package pe.com.cronos.core.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.exceptions.config.ErrorModel;
import pe.com.cronos.core.exceptions.domain.Message;
import pe.com.cronos.core.security.domain.CoreAuthenticatedUser;
import pe.com.cronos.core.security.domain.CoreSecurityProperties;
import pe.com.cronos.core.security.util.ErrorResponseUtil;
import pe.com.cronos.core.token.CoreTokenProvider;
import pe.com.cronos.core.token.domain.TokenValidationRequest;
import pe.com.cronos.core.token.domain.TokenValidationResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CoreTokenFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";

    private final CoreSecurityProperties coreSecurityProperties;
    private final CoreTokenProvider coreTokenProvider;
    private final ErrorResponseUtil errorResponseUtil;
    private final ObjectMapper objectMapper;
    private final AntPathMatcher pathMatcher;

    public CoreTokenFilter(CoreSecurityProperties coreSecurityProperties,
                           CoreTokenProvider coreTokenProvider,
                           ErrorResponseUtil errorResponseUtil,
                           ObjectMapper objectMapper) {
        this.coreSecurityProperties = coreSecurityProperties;
        this.coreTokenProvider = coreTokenProvider;
        this.errorResponseUtil = errorResponseUtil;
        this.objectMapper = objectMapper;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean success = validate(request, response);

        if (success)
            filterChain.doFilter(request, response);
    }

    private boolean validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tokenHeader = request.getHeader("Authorization");

            TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                    .token(tokenHeader.substring(BEARER.length()))
                    .build();

            TokenValidationResponse tokenValidationResponse = coreTokenProvider.validate(tokenValidationRequest);

            log.info("Core token filter, type token: {}, _uid: {}", tokenValidationResponse.getTokenType(), tokenValidationResponse.getId());

            List<SimpleGrantedAuthority> authorities = tokenValidationResponse.getAuthorities()
                    .stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UUID uuid = UUID.fromString(tokenValidationResponse.getId());
            CoreAuthenticatedUser coreAuthenticatedUser = new CoreAuthenticatedUser(tokenValidationResponse.getCredentialId(), null, authorities, uuid);
            SecurityContextHolder.getContext().setAuthentication(coreAuthenticatedUser);

            return true;
        } catch (CronosException ex) {
            log.error("Core token filter, error: {}", ex.getErrorInfo());
            response.setStatus(ex.getErrorInfo().getHttpStatus().value());
            ErrorModel errorModel = errorResponseUtil.map(request, ex.getErrorInfo());
            response.getWriter().write(objectMapper.writeValueAsString(errorModel));
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorModel errorModel = errorResponseUtil.map(request, Message.CORE_TOKEN_VALIDATION_UNKNOWN, HttpStatus.FORBIDDEN);
            log.error("Core token filter, unknown error: {}, {}", ex.getMessage(), errorModel);
            response.getWriter().write(objectMapper.writeValueAsString(errorModel));
        }

        return false;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return coreSecurityProperties.getExcludedPaths().stream().anyMatch(s -> pathMatcher.match(s, request.getServletPath()));
    }
}
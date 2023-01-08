package pe.com.cronos.core.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.token.CoreTokenProvider;
import pe.com.cronos.core.token.domain.TokenValidationRequest;
import pe.com.cronos.core.token.domain.TokenValidationResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CoreTokenFilter extends OncePerRequestFilter {
    private final CoreTokenProvider coreTokenProvider;
    private static final String BEARER = "Bearer ";

    public CoreTokenFilter(CoreTokenProvider coreTokenProvider) {
        this.coreTokenProvider = coreTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean success = validate(request, response);

        if (success)
            filterChain.doFilter(request, response);
    }

    private boolean validate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tokenHeader = request.getHeader("Authorization");

            TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                    .token(tokenHeader.substring(BEARER.length()))
                    .build();

            TokenValidationResponse tokenValidationResponse = coreTokenProvider.validate(tokenValidationRequest);

            log.info("Core token filter, type token: {}, _uid: {}", tokenValidationResponse.getTokenType(), tokenValidationResponse.getUid());

            List<SimpleGrantedAuthority> authorities = tokenValidationResponse.getAuthorities()
                    .stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(tokenValidationResponse.getId(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        } catch (CronosException ex) {
            log.error("Core token filter, error: {}", ex.getErrorInfo());
            response.setStatus(ex.getErrorInfo().getHttpStatus().value());
        } catch (Exception ex) {
            log.error("Core token filter, unknown error: {}", ex.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return false;
    }


}
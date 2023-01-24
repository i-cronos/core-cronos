package pe.com.cronos.core.security.wild.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.cronos.core.exceptions.config.ErrorModel;
import pe.com.cronos.core.exceptions.domain.Message;
import pe.com.cronos.core.security.wild.domain.CoreSecurityProperties;
import pe.com.cronos.core.security.wild.util.ErrorResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class CoreWardenFilter extends OncePerRequestFilter {
    private static final String SECURITY_HEADER = "Authorization";

    private final CoreSecurityProperties coreSecurityProperties;
    private final ErrorResponseUtil errorResponseUtil;
    private final ObjectMapper objectMapper;
    private final AntPathMatcher pathMatcher;


    public CoreWardenFilter(CoreSecurityProperties coreSecurityProperties, ErrorResponseUtil errorResponseUtil, ObjectMapper objectMapper) {
        this.coreSecurityProperties = coreSecurityProperties;
        this.errorResponseUtil = errorResponseUtil;
        this.objectMapper = objectMapper;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(SECURITY_HEADER);

        if (Objects.nonNull(tokenHeader)) {
            filterChain.doFilter(request, response);
        } else {
            log.info("Warden Filter, token is null to access in: {}", request.getServletPath());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ErrorModel errorModel = errorResponseUtil.map(request, Message.CORE_TOKEN_NOT_FOUND, HttpStatus.FORBIDDEN);
            response.getWriter().write(objectMapper.writeValueAsString(errorModel));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return coreSecurityProperties.getExcludedPaths().stream().anyMatch(s -> pathMatcher.match(s, request.getServletPath()));
    }
}

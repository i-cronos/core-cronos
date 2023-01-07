package pe.com.cronos.core.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class CoreWardenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        log.info("Warden Filter, token : {}", tokenHeader);
        if (Objects.nonNull(tokenHeader)) {
            filterChain.doFilter(request, response);
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}

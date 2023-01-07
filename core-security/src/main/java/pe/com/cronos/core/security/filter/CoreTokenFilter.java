package pe.com.cronos.core.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.cronos.core.token.TokenProvider;
import pe.com.cronos.core.token.domain.TokenValidationRequest;
import pe.com.cronos.core.token.domain.TokenValidationResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CoreTokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    public CoreTokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        log.info("Core token filter, token : {}", tokenHeader);
        if (Objects.nonNull(tokenHeader)) {
            TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                    .token(tokenHeader.replace("Bearer ", ""))
                    .build();

            TokenValidationResponse tokenValidationResponse = tokenProvider.validate(tokenValidationRequest);

            log.info("tokenValidationResponse : {}", tokenValidationResponse);

            List<SimpleGrantedAuthority> authorities = tokenValidationResponse.getAuthorities()
                    .stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            log.info("authorities :{} ", authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(tokenValidationResponse.getId(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


}
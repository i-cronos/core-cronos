package pe.com.cronos.core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pe.com.cronos.core.security.filter.CoreTokenFilter;
import pe.com.cronos.core.security.filter.CoreWardenFilter;
import pe.com.cronos.core.token.TokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class AppSecurityAutoConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, TokenProvider tokenProvider) throws Exception {
        http.csrf().disable();

        http.httpBasic().disable();

        http.addFilterAt(new CoreTokenFilter(tokenProvider), BasicAuthenticationFilter.class);
        http.addFilterBefore(new CoreWardenFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}

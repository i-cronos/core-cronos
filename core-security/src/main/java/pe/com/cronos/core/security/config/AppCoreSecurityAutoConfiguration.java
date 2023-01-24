package pe.com.cronos.core.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pe.com.cronos.core.security.domain.CoreSecurityProperties;
import pe.com.cronos.core.security.filter.CoreTokenFilter;
import pe.com.cronos.core.security.filter.CoreWardenFilter;
import pe.com.cronos.core.security.util.ErrorResponseUtil;
import pe.com.cronos.core.token.CoreTokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class AppCoreSecurityAutoConfiguration {

    @Bean
    public SecurityFilterChain coreSecurityFilterChain(HttpSecurity http, CoreSecurityProperties coreSecurityProperties, CoreTokenProvider tokenProvider) throws Exception {
        http.csrf().disable();

        http.httpBasic().disable();

        ErrorResponseUtil errorResponseUtil = new ErrorResponseUtil();
        ObjectMapper objectMapper = new ObjectMapper();

        http.addFilterAt(new CoreTokenFilter(coreSecurityProperties, tokenProvider, errorResponseUtil, objectMapper), BasicAuthenticationFilter.class);
        http.addFilterBefore(new CoreWardenFilter(coreSecurityProperties, errorResponseUtil, objectMapper), BasicAuthenticationFilter.class);

        return http.build();
    }
}

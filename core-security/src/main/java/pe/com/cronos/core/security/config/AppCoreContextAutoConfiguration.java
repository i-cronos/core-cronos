package pe.com.cronos.core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.security.service.CoreContextService;
import pe.com.cronos.core.security.service.DefaultCoreContextService;

@Configuration
public class AppCoreContextAutoConfiguration {

    @Bean
    public CoreContextService coreContextService() {
        return new DefaultCoreContextService();
    }
}
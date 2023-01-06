package pe.com.cronos.core.exceptions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreControllerAdviceAutoConfiguration {

    @Bean
    public CoreControllerExceptionHandler coreControllerExceptionHandler() {
        return new CoreControllerExceptionHandler();
    }
}

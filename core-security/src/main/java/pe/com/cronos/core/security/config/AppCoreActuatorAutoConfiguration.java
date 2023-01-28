package pe.com.cronos.core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.security.actuator.CoreCustomActuator;

@Configuration
public class AppCoreActuatorAutoConfiguration {

    @Bean
    public CoreCustomActuator coreCustomActuator() {
        return new CoreCustomActuator();
    }
}

package pe.com.cronos.core.aspect.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogExecutionAutoConfiguration {

    @Bean
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }
}

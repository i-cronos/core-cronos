package pe.com.cronos.core.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.aspect.log.LogExecutionAspect;
import pe.com.cronos.core.aspect.time.LogTimeAspect;

@Configuration
public class LogExecutionAutoConfiguration {

    @Bean
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }

    @Bean
    public LogTimeAspect logTimeAspect() {
        return new LogTimeAspect();
    }
}

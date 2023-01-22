package pe.com.cronos.core.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.util.current.CoreDateProviderUtil;
import pe.com.cronos.core.util.current.DefaultCoreDateProviderUtil;

@Configuration
public class CoreUtilAutoConfiguration {

    @Bean
    public CoreDateProviderUtil coreDateProviderUtil() {
        return new DefaultCoreDateProviderUtil();
    }
}

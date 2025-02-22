package restaurant.manager.RestaurantWebServiceWithDatabase.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import restaurant.manager.RestaurantWebServiceWithDatabase.Interceptors.TokenUsernameExtractorInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    TokenUsernameExtractorInterceptor extractorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(extractorInterceptor);
    }
}

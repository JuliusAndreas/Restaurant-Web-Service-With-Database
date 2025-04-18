package restaurant.manager.RestaurantWebServiceWithDatabase.Mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
    @Bean
    public RestaurantMapper restaurantMapper() {
        return Mappers.getMapper(RestaurantMapper.class);
    }
}

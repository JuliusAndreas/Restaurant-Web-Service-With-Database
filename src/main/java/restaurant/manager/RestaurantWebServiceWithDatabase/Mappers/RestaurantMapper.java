package restaurant.manager.RestaurantWebServiceWithDatabase.Mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "restaurant.id")
    @Mapping(target = "restaurantName", source = "restaurant.restaurantName")
    RestaurantDTO toDTO(Restaurant restaurant);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "restaurantName", source = "restaurantDTO.restaurantName")
    Restaurant fromDTO(RestaurantDTO restaurantDTO);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "restaurants.id")
    @Mapping(target = "restaurantName", source = "restaurants.restaurantName")
    List<RestaurantDTO> toDTO(Collection<Restaurant> restaurants);
}

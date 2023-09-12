package restaurant.manager.RestaurantWebServiceWithDatabase.Mappers;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
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
    @Mapping(target = "location", expression = "java(geometryToString(restaurants.getLocation()))")
    RestaurantDTO toDTO(Restaurant restaurant);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "restaurantName", source = "restaurantDTO.restaurantName")
    @Mapping(target = "location", expression = "java(stringToGeometry(restaurantDTO.getLocation()))")
    Restaurant fromDTO(RestaurantDTO restaurantDTO);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "restaurants.id")
    @Mapping(target = "restaurantName", source = "restaurants.restaurantName")
    @Mapping(target = "location", expression = "java(geometryToString(restaurants.getLocation()))")
    List<RestaurantDTO> toDTO(Collection<Restaurant> restaurants);

    default Geometry stringToGeometry(String wktPresentation) throws ParseException {
        return new WKTReader().read(wktPresentation);
    }

    default String geometryToString(Geometry geometry) {
        return new WKTWriter().write(geometry);
    }
}

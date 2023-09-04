package restaurant.manager.RestaurantWebServiceWithDatabase.DTOs;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public final class RestaurantDTO {

    @JsonView(Views.Private.class)
    private Integer id;

    @JsonView(Views.Public.class)
    private String restaurantName;

    public RestaurantDTO(Integer id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
    }

    public RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.restaurantName = restaurant.getRestaurantName();
    }
}

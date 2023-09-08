package restaurant.manager.RestaurantWebServiceWithDatabase.DTOs;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public final class RestaurantDTO {

    @JsonView(Views.Private.class)
    private Integer id;

    @JsonView(Views.Public.class)
    private String restaurantName;

    public RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.restaurantName = restaurant.getRestaurantName();
    }
}

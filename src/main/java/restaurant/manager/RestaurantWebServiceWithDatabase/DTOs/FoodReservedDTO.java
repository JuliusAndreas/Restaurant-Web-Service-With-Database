package restaurant.manager.RestaurantWebServiceWithDatabase.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FoodReservedDTO {
    private Integer foodId;
    private String foodName;
    private Integer quantity;
}

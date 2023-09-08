package restaurant.manager.RestaurantWebServiceWithDatabase.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public final class ReservationDTO {
    private Integer userId;
    private Integer foodId;
    private Integer quantity;
}

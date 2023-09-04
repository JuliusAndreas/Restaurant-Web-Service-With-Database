package restaurant.manager.RestaurantWebServiceWithDatabase.DTOs;

import lombok.NoArgsConstructor;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Restaurant;

import java.util.Objects;

@NoArgsConstructor(force = true)
public final class RestaurantDTO {
    private final Integer id;
    private final String restaurantName;

    public RestaurantDTO(Integer id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
    }

    public RestaurantDTO(Restaurant restaurant){
        this.id = restaurant.getId();
        this.restaurantName = restaurant.getRestaurantName();
    }

    public Integer id() {
        return id;
    }

    public String restaurantName() {
        return restaurantName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RestaurantDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantName);
    }

    @Override
    public String toString() {
        return "RestaurantRecord[" +
                "id=" + id + ", " +
                "restaurantName=" + restaurantName + ']';
    }
}

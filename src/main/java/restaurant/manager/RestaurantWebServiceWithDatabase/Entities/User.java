package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    private Integer id;

}

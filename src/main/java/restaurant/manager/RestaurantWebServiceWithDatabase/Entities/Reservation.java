package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @JsonView(value = Views.Private.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(value = Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @JsonView(value = Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Reservation(Food food, User user) {
        this.food = food;
        this.user = user;
    }
}

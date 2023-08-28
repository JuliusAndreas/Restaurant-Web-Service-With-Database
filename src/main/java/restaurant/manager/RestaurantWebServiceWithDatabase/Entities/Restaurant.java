package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @JsonView(value = Views.Private.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(value = Views.Public.class)
    @Column(name = "restaurantName")
    private String restaurantName;

    @JsonView(value = Views.Public.class)
    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods;

    @JsonView(value = Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void add(Food food) {
        if (this.foods == null) this.foods = new ArrayList<>();
        this.foods.add(food);
        food.setRestaurant(this);
    }

    public void remove(Food food) {
        if (this.foods == null) return;
        food.setRestaurant(null);
        this.foods.remove(food);
    }
}

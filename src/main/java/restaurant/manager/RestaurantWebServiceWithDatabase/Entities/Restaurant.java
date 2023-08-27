package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "restaurantName")
    private String restaurantName;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void add(Food food) {
        if (this.foods == null) this.foods = new ArrayList<>();
        this.foods.add(food);
        food.setRestaurant(this);
    }
}

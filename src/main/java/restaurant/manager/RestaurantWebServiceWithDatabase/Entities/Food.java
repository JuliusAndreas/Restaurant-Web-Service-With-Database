package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.FoodQuantity;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "foodName")
    private String foodName;

    @Enumerated(EnumType.STRING)
    @Column(name = "foodQuantity")
    private FoodQuantity foodQuantity;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ToString.Exclude
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Food(String foodName, FoodQuantity foodQuantity, Double rating, Double price) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.rating = rating;
        this.price = price;
    }
}

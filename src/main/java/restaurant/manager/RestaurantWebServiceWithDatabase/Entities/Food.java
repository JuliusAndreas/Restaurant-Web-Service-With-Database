package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.FoodQuantity;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;
import java.util.Objects;

@NamedEntityGraph(
        name = "food-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("foodName"),
                @NamedAttributeNode("foodQuantity"),
                @NamedAttributeNode("rating"),
                @NamedAttributeNode("price"),
        }
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @ToString.Exclude
    @JsonView(value = Views.Private.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(value = Views.Public.class)
    @Column(name = "foodName")
    private String foodName;

    @JsonView(value = Views.Public.class)
    @Column(name = "foodQuantity")
    private FoodQuantity foodQuantity;

    @JsonView(value = Views.Public.class)
    @Column(name = "rating")
    private Double rating;

    @JsonView(value = Views.Public.class)
    @Column(name = "price")
    private Double price;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY
            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Food(String foodName, FoodQuantity foodQuantity, Double rating, Double price) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.rating = rating;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName);
    }
}

package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "restaurant-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("restaurantName"),
                @NamedAttributeNode(value = "foods", subgraph = "foods-subgraph"),
                @NamedAttributeNode(value = "owner", subgraph = "owner-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "foods-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("foodName"),
                                @NamedAttributeNode("foodQuantity"),
                                @NamedAttributeNode("rating"),
                                @NamedAttributeNode("price"),
                        }
                ),
                @NamedSubgraph(
                        name = "owner-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("username"),
                                @NamedAttributeNode("password")
                        }
                )
        }
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    @Column(columnDefinition = "geometry")
    private Point location;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
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

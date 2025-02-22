package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.OrderStatus;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

@NamedEntityGraph(
        name = "reservation-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode(value = "food", subgraph = "food-subgraph"),
                @NamedAttributeNode(value = "user", subgraph = "user-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "food-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("foodName"),
                                @NamedAttributeNode("foodQuantity"),
                                @NamedAttributeNode("rating"),
                                @NamedAttributeNode("price"),
                        }
                ),
                @NamedSubgraph(
                        name = "user-subgraph",
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @JsonView(value = Views.Public.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonView(value = Views.Public.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @JsonView(value = Views.Public.class)
    @Column(name = "quantity")
    private Integer quantity;

    public Reservation(Food food, User user, OrderStatus status, Integer quantity) {
        this.food = food;
        this.user = user;
        this.status = status;
        this.quantity = quantity;
    }
}

package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
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

    public Reservation(Food food, User user) {
        this.food = food;
        this.user = user;
    }
}

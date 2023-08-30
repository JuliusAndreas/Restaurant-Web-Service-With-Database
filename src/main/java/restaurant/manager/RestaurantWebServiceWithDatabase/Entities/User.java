package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@NamedEntityGraph(
        name = "user-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("username"),
                @NamedAttributeNode("password")
        }
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @JsonView(value = Views.Private.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(value = Views.Public.class)
    @Column(name = "username")
    private String username;

    @JsonView(value = Views.Private.class)
    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

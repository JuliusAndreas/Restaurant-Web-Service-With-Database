package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;
import java.util.Set;

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
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @JsonIgnore
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
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Restaurant> restaurants;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Role> roles;

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        for (Role role : roles) {
            role.setUser(this);
        }
        this.roles = roles;
    }
}

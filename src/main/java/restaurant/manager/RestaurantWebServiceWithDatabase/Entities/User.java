package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

import java.util.List;

@Getter
@Setter
@ToString
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

    @JsonView(value = Views.Private.class)
    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @JsonView(value = Views.Private.class)
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

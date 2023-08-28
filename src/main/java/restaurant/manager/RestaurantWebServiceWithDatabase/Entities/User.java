package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

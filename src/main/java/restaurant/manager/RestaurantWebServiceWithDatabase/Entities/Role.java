package restaurant.manager.RestaurantWebServiceWithDatabase.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Views;

@NamedEntityGraph(
        name = "role-graph",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("roleName"),
                @NamedAttributeNode(value = "user", subgraph = "user-subgraph")
        },
        subgraphs = {
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
@Table(name = "role")
public class Role {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(value = Views.Public.class)
    @Column(name = "rolename")
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}

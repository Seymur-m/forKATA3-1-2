package ru.kata.spring.boot_security.demo.model;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;



@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    public Role(String name) {
        this.roleName = name;
    }

    public Role() {}

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String getAuthority() {
        if (!roleName.startsWith("ROLE_")) {
            return "ROLE_" + roleName;
        }
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
package tech.lab365.labmedical.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role {

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long role_id;

    @NotBlank
    @Column(nullable = false)
    private String roleName;

    public Role() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public @NotBlank String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank String roleName) {
        this.roleName = roleName;
    }
}

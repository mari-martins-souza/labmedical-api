package tech.lab365.labmedical.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID roleId;

    @NotBlank
    @Column(nullable = false)
    private String roleName;

    public Role() {
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public @NotBlank String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank String roleName) {
        this.roleName = roleName;
    }

}

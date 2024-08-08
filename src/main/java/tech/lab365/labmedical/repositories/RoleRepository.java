package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lab365.labmedical.entities.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByRoleName(String roleName);

    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findByRoleId(UUID roleId);
}

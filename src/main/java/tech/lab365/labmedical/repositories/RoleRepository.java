package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lab365.labmedical.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(String roleName);

    Role findByRoleName(String roleName);
}

package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lab365.labmedical.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}


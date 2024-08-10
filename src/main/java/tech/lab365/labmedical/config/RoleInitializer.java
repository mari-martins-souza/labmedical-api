package tech.lab365.labmedical.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.lab365.labmedical.entities.Role;
import tech.lab365.labmedical.repositories.RoleRepository;
import tech.lab365.labmedical.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_MEDICO", "ROLE_PACIENTE");

        for (String roleName : roles) {
            if (!roleRepository.existsByRoleName(roleName)) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleRepository.save(role);
            }
        }
    }
}


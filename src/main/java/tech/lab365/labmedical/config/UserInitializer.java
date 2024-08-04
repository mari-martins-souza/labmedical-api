package tech.lab365.labmedical.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.repositories.RoleRepository;
import tech.lab365.labmedical.repositories.UserRepository;

import java.time.LocalDate;

@Component
@Order(2)
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByEmail("admin@labmedical.com")) {

            User adminUser = new User();
            adminUser.setName("Master Admin");
            adminUser.setEmail("admin@labmedical.com");
            adminUser.setPassword(passwordEncoder.encode("adminlabmedical"));
            adminUser.setBirthdate(LocalDate.parse("1988-05-20"));
            adminUser.setCpf("000.000.000-01");
            adminUser.setRoleName("ROLE_ADMIN");

            userRepository.save(adminUser);
        }
    }
}

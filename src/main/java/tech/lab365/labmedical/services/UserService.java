package tech.lab365.labmedical.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.mappers.UserMapper;
import tech.lab365.labmedical.repositories.RoleRepository;
import tech.lab365.labmedical.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws BadRequestException {

        if (userRequestDTO.getName() == null || userRequestDTO.getName().isEmpty()) {
            throw new BadRequestException("name is mandatory");
        }

        if (userRequestDTO.getEmail() == null || userRequestDTO.getEmail().isEmpty()) {
            throw new BadRequestException("email is mandatory");
        }

        if (userRequestDTO.getBirthdate() == null) {
            throw new BadRequestException("birthdate is mandatory");
        }

        if (userRequestDTO.getCpf() == null || userRequestDTO.getCpf().isEmpty()) {
            throw new BadRequestException("cpf is mandatory");
        }

        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty()) {
            throw new BadRequestException("password is mandatory");
        }

        User user = userMapper.toUser(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);

    }
}

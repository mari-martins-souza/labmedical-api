package tech.lab365.labmedical.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.entities.Role;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    public User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword(dto.getPassword());

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName(dto.getRoleName());
        if (role != null) {
            roles.add(role);
        }
        user.setRoles(roles);

        return user;
    }

    public User toUser(PatientRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword("paciente");

        Role role = roleRepository.findByRoleName("PACIENTE");
        if (role != null) {
            user.setRoles(new HashSet<>(Arrays.asList(role)));
        }

        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setBirthdate(user.getBirthdate());
        dto.setCpf(user.getCpf());
        Set<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            dto.setRoleName(roles.iterator().next().getRoleName());
        }

        return dto;
    }

    public void updateUserFromDto(PatientRequestDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
    }




}

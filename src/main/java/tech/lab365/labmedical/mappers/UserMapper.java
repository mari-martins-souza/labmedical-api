package tech.lab365.labmedical.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.entities.User;
import tech.lab365.labmedical.repositories.RoleRepository;

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
        user.setRoleName(dto.getRoleName());
        return user;
    }

    public User toUser(PatientRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword("paciente");
        user.setRoleName("ROLE_PACIENTE");
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setBirthdate(user.getBirthdate());
        dto.setCpf(user.getCpf());
        dto.setRoleName((user.getRoleName()));
        return dto;
    }

    public void updateUserFromDto(PatientRequestDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
    }




}

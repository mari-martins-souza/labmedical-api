package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.PatientRequestDTO;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.entities.User;

@Component
public class UserMapper {
    public User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword(dto.getPassword());
        user.setProfile(dto.getProfile());
        return user;
    }

    public User toUser(PatientRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword("paciente");
        user.setProfile("patient");
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setBirthdate(user.getBirthdate());
        dto.setCpf(user.getCpf());
        dto.setProfile(user.getProfile());
        return dto;
    }

    public void updateUserFromDto(PatientRequestDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthdate(dto.getBirthdate());
        user.setCpf(dto.getCpf());
    }




}

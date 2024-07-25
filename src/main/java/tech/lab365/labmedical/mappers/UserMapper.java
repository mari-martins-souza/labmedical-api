package tech.lab365.labmedical.mappers;

import org.springframework.stereotype.Component;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.entities.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUserBirthdate(dto.getUserBirthdate());
        user.setCpf(dto.getCpf());
        user.setPassword(dto.getPassword());
        user.setProfile(dto.getProfile());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserBirthdate(user.getUserBirthdate());
        dto.setCpf(user.getCpf());
        dto.setProfile(user.getProfile());
        return dto;
    }
}

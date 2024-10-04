package tech.lab365.labmedical.security.dtos;

public record LoginResponseDTO(String token, Long expiresIn) {
}

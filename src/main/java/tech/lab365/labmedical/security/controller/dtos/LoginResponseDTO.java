package tech.lab365.labmedical.security.controller.dtos;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}

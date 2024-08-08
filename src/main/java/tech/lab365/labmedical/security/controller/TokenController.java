package tech.lab365.labmedical.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.lab365.labmedical.repositories.UserRepository;
import tech.lab365.labmedical.security.dtos.LoginRequestDTO;
import tech.lab365.labmedical.security.dtos.LoginResponseDTO;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        var user = userRepository.findByEmail(loginRequestDTO.email());

        if (user.isEmpty() || !user.get().isLoginValid(loginRequestDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("user or password invalid");
        }

        var now = Instant.now();
        var expiresIn = 30000L;

        var scope = user.get().getRoleName();

        var claims = JwtClaimsSet.builder()
                .issuer("labmedicalBackend")
                .subject(user.get().getUserId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .claim("scope", scope)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }

}

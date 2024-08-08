package tech.lab365.labmedical.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.lab365.labmedical.security.dtos.LoginRequestDTO;
import tech.lab365.labmedical.validation.Cpf;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    @NotBlank
    @Column(nullable = false)
    private String roleName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID userId;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthdate;

    @NotBlank
    @Cpf
    @Size(min = 11, max = 14)
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String password;

    public User() {
    }

    public @NotBlank String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank String roleName) {
        this.roleName = roleName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public @NotBlank @Size(max = 255) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 255) String name) {
        this.name = name;
    }

    public @NotBlank @Email @Size(max = 255) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(max = 255) String email) {
        this.email = email;
    }

    public @NotNull LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public @NotBlank @Size(min = 11, max = 14) String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank @Size(min = 11, max = 14) String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank @Size(max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(max = 255) String password) {
        this.password = password;
    }

    public boolean isLoginValid(LoginRequestDTO loginRequestDTO, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequestDTO.password(), this.password);
    }


}

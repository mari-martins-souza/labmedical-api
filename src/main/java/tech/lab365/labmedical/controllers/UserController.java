package tech.lab365.labmedical.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.lab365.labmedical.dtos.UserRequestDTO;
import tech.lab365.labmedical.dtos.UserResponseDTO;
import tech.lab365.labmedical.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws BadRequestException {
        UserResponseDTO savedUser = userService.saveUser(userRequestDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}


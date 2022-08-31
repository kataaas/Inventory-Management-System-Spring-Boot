package ru.kataaas.ims.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataaas.ims.dto.RegisterUserDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO fetchUser(@PathVariable Long id) {
        Optional<UserEntity> user = userService.findById(id);
        return user.map(userMapper::toUserDTO).orElse(null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterUserDTO registerDTO) {
        if (userService.checkIfPhoneNumberAlreadyUsed(registerDTO.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("The phone number is already in use");
        }
        if (userService.checkIfEmailAlreadyUsed(registerDTO.getEmail())) {
            return ResponseEntity.badRequest().body("The email is already in use");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(registerDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

}

package ru.kataaas.ims.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kataaas.ims.dto.RegisterDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO createUser(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.create(registerDTO);
    }

}

package ru.kataaas.ims.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.RegisterDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.repository.RoleRepository;
import ru.kataaas.ims.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final CartService cartService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserMapper userMapper,
                       @Lazy CartService cartService,
                       UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO create(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(registerDTO.getFirstName());
        user.setSecondName(registerDTO.getSecondName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        user.setCity(registerDTO.getCity());
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));

        UserEntity savedUser = userRepository.save(user);
        cartService.create(savedUser.getId());
        return userMapper.toUserDTO(savedUser);
    }

    public boolean checkIfPhoneNumberAlreadyUsed(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean checkIfEmailAlreadyUsed(String email) {
        return userRepository.existsByEmail(email);
    }

}

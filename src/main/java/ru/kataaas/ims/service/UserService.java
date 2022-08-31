package ru.kataaas.ims.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataaas.ims.dto.RegisterUserDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final CartService cartService;

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper,
                       @Lazy CartService cartService,
                       UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long findIdByPhoneNumber(String phoneNumber) {
        return userRepository.findIdByPhoneNumber(phoneNumber);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public UserDTO create(RegisterUserDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(registerDTO.getFirstName());
        user.setSecondName(registerDTO.getSecondName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setCity(registerDTO.getCity());

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

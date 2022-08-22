package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.RegisterDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public UserDTO create(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(registerDTO.getFirstName());
        user.setSecondName(registerDTO.getSecondName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        user.setCity(registerDTO.getCity());

        UserEntity savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

    public boolean checkIfPhoneNumberAlreadyUsed(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean checkIfEmailAlreadyUsed(String email) {
        return userRepository.existsByEmail(email);
    }

}

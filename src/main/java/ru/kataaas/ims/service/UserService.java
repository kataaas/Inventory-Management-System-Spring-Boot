package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.RegisterDTO;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(registerDTO.getFirstName());
        user.setSecondName(registerDTO.getSecondName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setEmail(registerDTO.getEmail());
        user.setCity(registerDTO.getCity());

        UserEntity savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getSecondName(),
                savedUser.getPhoneNumber(), savedUser.getEmail(), savedUser.getCity(),
                savedUser.getCreatedAt(), null);
    }

}

package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.AuthResponse;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;

@Service
public class UserMapper {

    public UserDTO toUserDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public AuthResponse toAuthResponse(UserEntity user, String token) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(user.getId());
        authResponse.setLogin(user.getPhoneNumber());
        authResponse.setAccessToken(token);
        return authResponse;
    }
}

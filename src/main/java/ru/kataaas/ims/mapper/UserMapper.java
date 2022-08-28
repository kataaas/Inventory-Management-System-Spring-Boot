package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.AuthUserResponse;
import ru.kataaas.ims.dto.UserDTO;
import ru.kataaas.ims.entity.UserEntity;

@Service
public class UserMapper {

    public UserDTO toUserDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(userDTO.getSecondName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public AuthUserResponse toAuthResponse(UserEntity user, String token) {
        AuthUserResponse authUserResponse = new AuthUserResponse();
        authUserResponse.setId(user.getId());
        authUserResponse.setLogin(user.getPhoneNumber());
        authUserResponse.setAccessToken(token);
        return authUserResponse;
    }
}

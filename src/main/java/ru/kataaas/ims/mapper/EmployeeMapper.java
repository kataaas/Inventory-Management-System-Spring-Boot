package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.AuthResponse;
import ru.kataaas.ims.entity.EmployeeEntity;

@Service
public class EmployeeMapper {

    public AuthResponse toAuthResponse(EmployeeEntity employee, String token) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(employee.getId());
        authResponse.setLogin(employee.getLogin());
        authResponse.setAccessToken(token);
        return authResponse;
    }

}

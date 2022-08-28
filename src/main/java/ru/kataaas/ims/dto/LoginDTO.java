package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    // email or phoneNumber
    @Pattern(regexp = "(^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$|\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}$)")
    private String login;

    @Size(min = 8)
    private String password;

}

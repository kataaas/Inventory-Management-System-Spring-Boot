package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginVendorDTO {

    @Email
    private String login;

    @Size(min = 8)
    private String password;

}

package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class RegisterDTO {

    private String firstName;

    private String secondName;

    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "must be +7-***-***-**-**")
    private String phoneNumber;

    @Email
    private String email;

    private String password;

    private String city;

}

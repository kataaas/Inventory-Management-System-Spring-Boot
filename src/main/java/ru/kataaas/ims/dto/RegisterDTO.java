package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class RegisterDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "must be +7-***-***-**-**")
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 24)
    private String password;

    @NotNull
    private String city;

}

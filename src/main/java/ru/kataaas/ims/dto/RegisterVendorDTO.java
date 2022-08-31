package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterVendorDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "must be +7-***-***-**-**")
    private String phoneNumber;

    @NotNull
    @Size(min = 8, max = 24)
    private String password;

}

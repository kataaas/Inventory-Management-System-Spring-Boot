package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @Size(min = 8, max = 24)
    private String password;

}

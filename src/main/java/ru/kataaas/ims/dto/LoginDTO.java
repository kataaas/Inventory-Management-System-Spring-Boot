package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotNull
    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}$")
    private String login;

    @NotNull
    @Size(min = 8)
    private String password;

}


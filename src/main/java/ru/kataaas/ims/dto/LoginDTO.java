package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotNull
    @Size(min = 8, max = 24)
    private String login;

    @NotNull
    @Size(min = 8)
    private String password;

}

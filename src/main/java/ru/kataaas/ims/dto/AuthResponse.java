package ru.kataaas.ims.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private Long id;

    private String login;

    private String accessToken;

}

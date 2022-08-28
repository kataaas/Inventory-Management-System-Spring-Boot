package ru.kataaas.ims.dto;

import lombok.Data;

@Data
public class AuthUserResponse {

    private Long id;

    private String username;

    private String accessToken;

}

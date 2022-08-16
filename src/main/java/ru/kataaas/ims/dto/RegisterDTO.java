package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {

    private String firstName;

    private String secondName;

    private String phoneNumber;

    private String email;

    private String city;

}

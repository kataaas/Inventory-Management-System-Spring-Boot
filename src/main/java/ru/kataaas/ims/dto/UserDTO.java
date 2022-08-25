package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String secondName;

    private String phoneNumber;

    private String email;

    private String city;

    private Date createdAt;

}

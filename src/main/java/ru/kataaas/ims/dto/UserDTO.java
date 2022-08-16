package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.kataaas.ims.entity.CartEntity;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String secondName;

    private String phoneNumber;

    private String email;

    private String city;

    private Date createdAt;

    private Set<CartEntity> cart;

}

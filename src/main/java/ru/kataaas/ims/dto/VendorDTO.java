package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private Long id;

    private String name;

    private String email;

    private Date createdAt;

    private int products;

}

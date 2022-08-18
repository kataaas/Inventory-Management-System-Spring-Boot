package ru.kataaas.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class VendorDTO {

    private Long id;

    private String name;

    private String email;

    private Date createdAt;

    private Set<ProductDTO> products;

}

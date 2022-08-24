package ru.kataaas.ims.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CartDTO {

    private Long id;

    private List<ProductDTO> products;

    private boolean ordered;

    private Date orderTime;

}

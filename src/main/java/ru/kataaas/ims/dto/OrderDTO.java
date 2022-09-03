package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDTO {

    @NotNull
    private Long id;

    @NotNull
    private ProductDTO product;

    @Min(1)
    private int quantity;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date orderTime;

}

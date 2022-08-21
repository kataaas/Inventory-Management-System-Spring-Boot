package ru.kataaas.ims.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CreateProductDTO {

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @Min(1)
    @NotNull
    private BigDecimal price;

    @Min(0)
    @NotNull
    private int quantity;

    @NotNull
    private CategoryDTO subCategory;

}

package ru.kataaas.ims.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private List<ProductDTO> products;

    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean last;

}

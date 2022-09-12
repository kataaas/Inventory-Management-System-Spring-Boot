package ru.kataaas.ims.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.CategoryDTO;
import ru.kataaas.ims.dto.ProductDTO;
import ru.kataaas.ims.dto.ProductResponse;
import ru.kataaas.ims.entity.CategoryEntity;
import ru.kataaas.ims.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    public ProductResponse toProductResponse(Page<ProductEntity> products) {
        ProductResponse productResponse = new ProductResponse();
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.get().forEach(product -> productsDTO.add(toProductDTO(product)));

        productResponse.setProducts(productsDTO);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

    public ProductDTO toProductDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setCategory(toCategoryDTO(product.getCategory()));
        productDTO.setVendorName(product.getVendor().getName());
        productDTO.setDiscountPercent(product.getStock() != null ? product.getStock().getDiscountPercent() : 0);

        return productDTO;
    }


    public CategoryDTO toCategoryDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setSubcategory(category.getSubcategory());
        categoryDTO.setCategory(category.getCategory());

        return categoryDTO;
    }

}

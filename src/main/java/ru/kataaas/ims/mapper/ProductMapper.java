package ru.kataaas.ims.mapper;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.CategoryDTO;
import ru.kataaas.ims.dto.ProductDTO;
import ru.kataaas.ims.entity.CategoryEntity;
import ru.kataaas.ims.entity.ProductEntity;

@Service
public class ProductMapper {

    public ProductDTO toProductDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setCategory(toCategoryDTO(product.getCategory()));
        productDTO.setVendorName(product.getVendor().getName());
        productDTO.setDiscountPercent(product.getStock().getDiscountPercent());

        return productDTO;
    }


    public CategoryDTO toCategoryDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setSubcategory(category.getSubcategory());
        categoryDTO.setCategory(category.getCategory());

        return categoryDTO;
    }

}

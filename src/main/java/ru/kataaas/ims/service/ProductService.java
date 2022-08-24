package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.dto.CreateProductDTO;
import ru.kataaas.ims.dto.ProductDTO;
import ru.kataaas.ims.entity.CartProductsEntity;
import ru.kataaas.ims.entity.CategoryEntity;
import ru.kataaas.ims.entity.ProductEntity;
import ru.kataaas.ims.entity.VendorEntity;
import ru.kataaas.ims.mapper.ProductMapper;
import ru.kataaas.ims.repository.CategoryRepository;
import ru.kataaas.ims.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    private final VendorService vendorService;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductMapper productMapper,
                          VendorService vendorService,
                          ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productMapper = productMapper;
        this.vendorService = vendorService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDTO create(CreateProductDTO productDTO, Long vendorId) {
        ProductEntity product = new ProductEntity();
        VendorEntity vendor = vendorService.findById(vendorId);
        CategoryEntity category = getCategoryBySubcategory(productDTO.getSubcategory());

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(category);
        product.setVendor(vendor);

        ProductEntity savedProduct = productRepository.save(product);
        return productMapper.toProductDTO(savedProduct);
    }

    public CategoryEntity getCategoryBySubcategory(String subcategory) {
        return categoryRepository.getBySubcategory(subcategory);
    }

    public int getQuantityById(Long id) {
        return productRepository.getQuantityProduct(id);
    }

}

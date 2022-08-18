package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}

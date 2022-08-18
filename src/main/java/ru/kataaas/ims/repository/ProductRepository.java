package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

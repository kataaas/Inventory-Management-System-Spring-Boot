package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kataaas.ims.entity.ProductEntity;

import java.util.Set;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Set<ProductEntity> findByIdIsIn(Set<Long> ids);

    @Query(value = "SELECT p.quantity FROM product p WHERE p.id = :id", nativeQuery = true)
    int getQuantityProduct(@Param("id") Long id);

}

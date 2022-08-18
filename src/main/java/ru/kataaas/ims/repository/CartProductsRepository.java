package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.CartProductsEntity;
import ru.kataaas.ims.entity.CartProductsKey;

public interface CartProductsRepository extends JpaRepository<CartProductsEntity, CartProductsKey> {
}

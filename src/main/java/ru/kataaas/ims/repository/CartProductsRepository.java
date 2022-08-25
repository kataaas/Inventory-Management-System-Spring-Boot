package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.CartProductsEntity;
import ru.kataaas.ims.entity.CartProductsKey;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CartProductsRepository extends JpaRepository<CartProductsEntity, CartProductsKey> {

    Optional<CartProductsEntity> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartProductsEntity> findByCartIdAndProductIdIsIn(Long cartId, Set<Long> ids);

    List<CartProductsEntity> findProductsByCart_Id(Long cartId);

}

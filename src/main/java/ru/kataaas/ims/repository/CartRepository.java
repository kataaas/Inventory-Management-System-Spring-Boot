package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.CartEntity;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> getByUserId(Long userId);

}

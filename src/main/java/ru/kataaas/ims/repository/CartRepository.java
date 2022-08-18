package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}

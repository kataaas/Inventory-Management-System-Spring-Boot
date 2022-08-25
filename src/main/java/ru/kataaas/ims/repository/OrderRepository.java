package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

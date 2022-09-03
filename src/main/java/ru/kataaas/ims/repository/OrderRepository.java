package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUser_PhoneNumber(String phoneNumber);

}

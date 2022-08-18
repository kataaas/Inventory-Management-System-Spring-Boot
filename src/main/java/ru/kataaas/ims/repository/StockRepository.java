package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}

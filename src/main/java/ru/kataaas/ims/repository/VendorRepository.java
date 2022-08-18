package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.VendorEntity;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}

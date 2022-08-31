package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.VendorEntity;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    Optional<VendorEntity> findByName(String name);

    VendorEntity findByEmail(String email);

    VendorEntity findByPhoneNumber(String phoneNumber);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}

package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kataaas.ims.entity.VendorEntity;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    @Query(value = "SELECT v.id FROM vendor v WHERE v.phone_number = :phoneNumber", nativeQuery = true)
    Long findIdByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<VendorEntity> findByName(String name);

    VendorEntity findByEmail(String email);

    VendorEntity findByPhoneNumber(String phoneNumber);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}

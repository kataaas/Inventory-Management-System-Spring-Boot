package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kataaas.ims.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u.id FROM user_t u WHERE u.phone_number = :phoneNumber", nativeQuery = true)
    Long findIdByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    UserEntity findByPhoneNumberOrEmail(String phoneNumber, String email);

    UserEntity findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}

package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

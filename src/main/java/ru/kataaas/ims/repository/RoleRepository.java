package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    boolean existsByName(String name);

}

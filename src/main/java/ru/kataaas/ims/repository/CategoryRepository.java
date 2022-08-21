package ru.kataaas.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataaas.ims.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    CategoryEntity getBySubcategory(String subcategory);

}

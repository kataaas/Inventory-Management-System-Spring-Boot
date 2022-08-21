package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    private String subcategory;

    @NotNull
    private String category;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products = new HashSet<>();

}

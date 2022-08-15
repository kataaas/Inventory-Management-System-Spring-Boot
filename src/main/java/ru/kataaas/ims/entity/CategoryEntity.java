package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;

}

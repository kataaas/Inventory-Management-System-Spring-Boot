package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date issued;

    private Date expiration;

    @NotNull
    private int discountPercent;

    @OneToMany(mappedBy = "stock")
    private Set<ProductEntity> products;

    @NotNull
    @ManyToOne
    private VendorEntity vendor;

}

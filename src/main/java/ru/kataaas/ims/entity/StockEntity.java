package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date issued;

    private Date expiration;

    private int discountPercent;

    @OneToMany(mappedBy = "stock")
    private Set<ProductEntity> products;

    @ManyToOne
    private VendorEntity vendor;

}

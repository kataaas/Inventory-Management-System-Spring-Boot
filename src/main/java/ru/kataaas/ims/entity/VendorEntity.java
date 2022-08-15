package ru.kataaas.ims.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "vendor")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "vendor")
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "vendor")
    private Set<StockEntity> stocks;

}

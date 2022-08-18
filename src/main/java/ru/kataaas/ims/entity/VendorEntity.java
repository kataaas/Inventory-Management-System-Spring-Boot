package ru.kataaas.ims.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "vendor")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 24)
    private String password;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "vendor")
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "vendor")
    private Set<StockEntity> stocks;

}

package ru.kataaas.ims.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private int quantity;

    @CreationTimestamp
    private Date createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_name")
    private CategoryEntity category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendor;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stock;

}

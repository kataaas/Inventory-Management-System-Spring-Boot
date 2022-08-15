package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "quantity_product")
public class QuantityProductEntity implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    ProductEntity product;

    private int quantity;

}

package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cart_products")
public class CartProductsEntity implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    private int quantity;

}

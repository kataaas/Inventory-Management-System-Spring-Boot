package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cart_products")
@IdClass(CartProductsKey.class)
public class CartProductsEntity {

    @Id
    private Long cartId;

    @Id
    private Long productId;

    @MapsId("cartId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @MapsId("productId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Min(0)
    private int quantity;

}

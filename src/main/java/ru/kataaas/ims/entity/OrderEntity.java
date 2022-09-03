package ru.kataaas.ims.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Entity
@Table(name = "order_product")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Min(1)
    private int quantity;

    @CreationTimestamp
    private Date createdAt;

    private Date orderTime;

    private boolean delivered;

}

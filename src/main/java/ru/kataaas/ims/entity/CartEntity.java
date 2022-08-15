package ru.kataaas.ims.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private boolean ordered;

    private Date orderTime;

}

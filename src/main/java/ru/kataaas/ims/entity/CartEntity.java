package ru.kataaas.ims.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

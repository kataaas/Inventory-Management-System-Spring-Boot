package ru.kataaas.ims.entity;

import javax.persistence.*;

@Entity
@Table(name = "promo")
public class PromoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

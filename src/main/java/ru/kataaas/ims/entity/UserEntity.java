package ru.kataaas.ims.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_t")
@RequiredArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}")
    private String phoneNumber;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String city;

    @CreationTimestamp
    private Date lastModifiedDateTime;

    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "user")
    private CartEntity cart;

    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> orders = new HashSet<>();

}

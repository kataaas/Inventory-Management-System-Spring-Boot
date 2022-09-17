package ru.kataaas.ims.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 8)
    @Column(unique = true)
    private String login;

    @NotNull
    @Size(min = 8, max = 24)
    private String password;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}")
    private String phoneNumber;

    @NotNull
    private String post;

    @CreationTimestamp
    private Date lastModifiedDateTime;

    @CreationTimestamp
    private Date createdAt;

}

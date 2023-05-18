package com.btl.book.entity;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Username is not empty!")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is not empty!")
    private String password;

    @NotBlank(message = "Email is not empty!")
    private String email;

}

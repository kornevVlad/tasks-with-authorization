package com.my.project.model;

import com.my.project.enums.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
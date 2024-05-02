package com.dev.model;

import com.dev.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "user")
@Getter
@Setter
public class UserModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;
    private String phoneNumber;
    private Date birthDate;

    @Enumerated(value = EnumType.STRING)
    private Role role;


}

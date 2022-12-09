package com.onejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 128)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<AuthUserGroup> authUserGroups = new LinkedHashSet<>();

}
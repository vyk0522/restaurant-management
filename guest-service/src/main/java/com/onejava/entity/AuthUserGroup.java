package com.onejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_user_group")
public class AuthUserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTH_USER_GROUP_ID", nullable = false)
    private Long id;

    @Column(name = "auth_group", nullable = false, length = 128)
    private String authGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USERNAME", nullable = false, referencedColumnName = "USERNAME")
    private User user;

}
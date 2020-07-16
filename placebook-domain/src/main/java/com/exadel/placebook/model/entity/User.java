package com.exadel.placebook.model.entity;


import com.exadel.placebook.model.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "password")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;
}

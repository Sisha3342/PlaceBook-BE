package com.exadel.placebook.model.entity;


import com.exadel.placebook.model.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
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

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "hr_id")
    private Long hrId;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Booking> bookings;
}

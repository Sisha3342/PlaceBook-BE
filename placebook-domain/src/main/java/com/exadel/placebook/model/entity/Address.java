package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="address")
@Entity

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;
}
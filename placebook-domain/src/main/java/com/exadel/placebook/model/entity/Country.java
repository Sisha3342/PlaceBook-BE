package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="country")
@Entity

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;
}

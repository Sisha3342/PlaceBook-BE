package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name= "city")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;
}

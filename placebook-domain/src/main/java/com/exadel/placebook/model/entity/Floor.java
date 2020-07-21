package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "floors")
@Data
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "floor_configuration")
    private String floorConfiguration;

}

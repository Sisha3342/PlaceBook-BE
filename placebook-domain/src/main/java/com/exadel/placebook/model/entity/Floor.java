package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "floors")
@Data
public class Floor extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "floor_configuration")
    private String floorConfiguration;

}

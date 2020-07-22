package com.exadel.placebook.model.entity;

import com.exadel.placebook.model.enums.PlaceStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "place")
@Data
public class Place extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Column(name = "place_number")
    private String placeNumber;

    @Column(name="status")
    private PlaceStatus placeStatus;
}

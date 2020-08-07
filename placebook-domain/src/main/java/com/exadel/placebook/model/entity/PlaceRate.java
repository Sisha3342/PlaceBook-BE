package com.exadel.placebook.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "place_rate")
@Getter
@Setter
@RequiredArgsConstructor
public class PlaceRate extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "mark_lightning")
    private Long markLightning;

    @Column(name = "mark_air")
    private Long markAir;

    @Column(name = "mark_volume")
    private Long markVolume;

    @Column(name = "mark_cleaning")
    private Long markCleaning;

    @Column(name = "mark_location")
    private Long markLocation;

    @Column(name = "feedback")
    private String feedback;
}

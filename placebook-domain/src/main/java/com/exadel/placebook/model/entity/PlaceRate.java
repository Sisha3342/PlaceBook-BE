package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "place_rate")
@Data
public class PlaceRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

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
    private Long feedback;
}
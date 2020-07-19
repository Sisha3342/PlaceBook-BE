package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "office")
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "worktime_start")
    private Time workTimeStart;

    @Column(name = "worktime_end")
    private Time workTimeEnd;

    @Column(name = "floor_number")
    private int floorNumber;
}

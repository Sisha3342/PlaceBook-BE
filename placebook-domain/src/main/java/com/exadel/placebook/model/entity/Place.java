package com.exadel.placebook.model.entity;

import com.exadel.placebook.model.enums.PlaceStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "place")
@Getter
@Setter
@RequiredArgsConstructor
public class Place extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Column(name = "place_number")
    private String placeNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PlaceStatus placeStatus;

    @Column(name="capacity")
    private long capacity;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<Booking> bookings;
}

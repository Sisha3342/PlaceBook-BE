package com.exadel.placebook.model.entity;


import com.exadel.placebook.model.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@RequiredArgsConstructor
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}

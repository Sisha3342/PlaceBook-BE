package com.exadel.placebook.model.entity;


import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="time_start")
    private LocalDateTime timeStart;

    @Column(name="time_end")
    private LocalDateTime timeEnd;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


}

package com.exadel.placebook.model.entity;


import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "time_start")
    private Timestamp timeStart;

    @Column(name = "time_end")
    private Timestamp timeEnd;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}

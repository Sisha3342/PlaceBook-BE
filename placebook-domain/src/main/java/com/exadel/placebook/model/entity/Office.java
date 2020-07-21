package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "office")
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "worktime_start")
    private LocalTime workTimeStart;

    @Column(name = "worktime_end")
    private LocalTime workTimeEnd;
}

package com.exadel.placebook.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "office")
@Getter
@Setter
@RequiredArgsConstructor
public class Office extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "worktime_start")
    private LocalTime workTimeStart;

    @Column(name = "worktime_end")
    private LocalTime workTimeEnd;

    @Column(name="deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "office",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Floor> floors;
}

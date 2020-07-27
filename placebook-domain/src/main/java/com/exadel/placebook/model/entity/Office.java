package com.exadel.placebook.model.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "office")
@Data
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

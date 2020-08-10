package com.exadel.placebook.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floors")
@Getter
@Setter
@RequiredArgsConstructor
public class Floor extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "floor_configuration")
    private String floorConfiguration;

    @Column(name="floor_number")
    private String floorNumber;

    @Column(name="deleted")
    private boolean deleted;

    @Column(name = "width")
    private Long width;

    @Column(name = "height")
    private Long height;

    @OneToMany(mappedBy = "floor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Place> places;
}

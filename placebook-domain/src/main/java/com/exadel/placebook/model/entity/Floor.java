package com.exadel.placebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floors")
@Data
public class Floor extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "floor_configuration")
    private String floorConfiguration;

    @Column(name="floor_number")
    private String floorNumber;

    @Column(name="deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "floor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Place> places;
}

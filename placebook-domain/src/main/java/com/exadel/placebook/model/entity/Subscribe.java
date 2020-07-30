package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subscribe_to_place")
@Data
public class Subscribe extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}

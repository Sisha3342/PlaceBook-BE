package com.exadel.placebook.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subscribe_to_place")
@Data
public class Subscribe extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

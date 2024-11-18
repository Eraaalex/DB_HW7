package com.hse.db.hw.hw7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results")
@IdClass(ResultId.class)
@Getter
@Setter
public class Result {

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "medal", length = 7)
    private String medal;
    @Column(name = "result")
    private Double result;
}

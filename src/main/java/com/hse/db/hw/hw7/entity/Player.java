package com.hse.db.hw.hw7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {

    @Id
    @Column(name = "player_id", length = 10)
    private String playerId;

    @Column(name = "name", length = 40)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "player")
    private List<Result> results;

}

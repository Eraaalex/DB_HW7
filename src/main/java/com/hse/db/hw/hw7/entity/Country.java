package com.hse.db.hw.hw7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {

    @Id
    @Column(name = "country_id", length = 3)
    private String countryId;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "area_sqkm")
    private Integer areaSqkm;

    @Column(name = "population")
    private Integer population;

    @OneToMany(mappedBy = "country")
    private List<Player> players;
    @OneToMany(mappedBy = "country")
    private List<Olympics> hostedOlympics;

}
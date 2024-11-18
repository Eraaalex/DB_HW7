package com.hse.db.hw.hw7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "olympics")
@Getter
@Setter
public class Olympics {

    @Id
    @Column(name = "olympic_id", length = 7)
    private String olympicId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "year")
    private Integer year;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

    @OneToMany(mappedBy = "olympics")
    private List<Event> events;
}


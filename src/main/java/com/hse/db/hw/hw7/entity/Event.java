package com.hse.db.hw.hw7.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @Column(name = "event_id", length = 7)
    private String eventId;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "eventtype", length = 20)
    private String eventType;

    @ManyToOne
    @JoinColumn(name = "olympic_id")
    private Olympics olympics;

    @Column(name = "is_team_event")
    private Integer isTeamEvent;

    @Column(name = "num_players_in_team")
    private Integer numPlayersInTeam;

    @Column(name = "result_noted_in", length = 100)
    private String resultNotedIn;

    @OneToMany(mappedBy = "event")
    private List<Result> results;

}

create table events
(
    event_id            char(7)
        unique,
    name                char(40),
    eventtype           char(20),
    olympic_id          char(7)
        references olympics (olympic_id),
    is_team_event       integer
        constraint events_is_team_event_check
            check (is_team_event = ANY (ARRAY [0, 1])),
    num_players_in_team integer,
    result_noted_in     char(100)
);
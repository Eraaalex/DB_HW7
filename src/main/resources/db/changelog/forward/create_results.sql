create table results
(
    event_id  char(7)
        references events (event_id),
    player_id char(10)
        references players (player_id),
    medal     char(7),
    result    double precision
);
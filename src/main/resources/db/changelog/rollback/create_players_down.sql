create table players
(
    name       char(40),
    player_id  char(10)
        unique,
    country_id char(3)
        references countries (country_id),
    birthdate  date
);
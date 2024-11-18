create table olympics
(
    olympic_id char(7)
        unique,
    country_id char(3)
        references countries (country_id),
    city       char(50),
    year       integer,
    startdate  date,
    enddate    date
);

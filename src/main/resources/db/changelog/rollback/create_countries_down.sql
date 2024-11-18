create table countries
(
    name       char(40),
    country_id char(3)
        unique,
    area_sqkm  integer,
    population integer
);
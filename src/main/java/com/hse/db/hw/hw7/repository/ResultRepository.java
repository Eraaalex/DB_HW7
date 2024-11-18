package com.hse.db.hw.hw7.repository;

import com.hse.db.hw.hw7.projection.BirthYearMedalStats;
import com.hse.db.hw.hw7.entity.Result;
import com.hse.db.hw.hw7.entity.ResultId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, ResultId> {

    // Task 1
    @Query(value = "SELECT EXTRACT(YEAR FROM p.birthdate) AS birthYear, "
            + "COUNT(DISTINCT p.player_id) AS playerCount, "
            + "COUNT(r.medal) AS goldMedalCount "
            + "FROM results r "
            + "JOIN players p ON r.player_id = p.player_id "
            + "JOIN events e ON r.event_id = e.event_id "
            + "JOIN olympics o ON e.olympic_id = o.olympic_id "
            + "WHERE o.year = 2004 AND r.medal = 'GOLD' "
            + "GROUP BY birthYear", nativeQuery = true)
    List<BirthYearMedalStats> findBirthYearMedalStatsFor2004Olympics();
}
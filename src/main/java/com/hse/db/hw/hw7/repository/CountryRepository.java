package com.hse.db.hw.hw7.repository;

import com.hse.db.hw.hw7.projection.CountryMedalRatio;
import com.hse.db.hw.hw7.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

    @Query(value = "SELECT c.name AS countryName, "
            + "(COUNT(r.medal)::float / c.population) AS medalToPopulationRatio "
            + "FROM countries c "
            + "JOIN players p ON p.country_id = c.country_id "
            + "JOIN results r ON r.player_id = p.player_id "
            + "JOIN events e ON r.event_id = e.event_id "
            + "JOIN olympics o ON e.olympic_id = o.olympic_id "
            + "WHERE o.year = 2000 AND e.is_team_event = 1 AND r.medal IN ('GOLD', 'SILVER', 'BRONZE') "
            + "GROUP BY c.name, c.population "
            + "ORDER BY medalToPopulationRatio ASC "
            + "LIMIT 5", nativeQuery = true)
    List<CountryMedalRatio> findCountriesWithMinimalTeamMedalRatio();

}
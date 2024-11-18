package com.hse.db.hw.hw7.repository;

import com.hse.db.hw.hw7.dto.CountryStartedVowelDTO;
import com.hse.db.hw.hw7.dto.PlayerDTO;
import com.hse.db.hw.hw7.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {

    // Task 3
    @Query("SELECT DISTINCT new com.hse.db.hw.hw7.dto.PlayerDTO(p.name, o.olympicId) "
            + "FROM Result r "
            + "JOIN r.player p "
            + "JOIN r.event e "
            + "JOIN e.olympics o "
            + "WHERE r.medal IN ('GOLD', 'SILVER', 'BRONZE')")
    List<PlayerDTO> findPlayersWithMedalsInOlympic();

    // Task 4
    @Query("SELECT new com.hse.db.hw.hw7.dto.CountryStartedVowelDTO(c.name, "
            + "(COUNT(CASE WHEN LOWER(SUBSTRING(p.name, 1, 1)) IN ('a','e','i','o','u') THEN 1 END) * 1.0) / COUNT(p.playerId)) "
            + "FROM Player p "
            + "JOIN p.country c "
            + "GROUP BY c.name "
            + "ORDER BY ((COUNT(CASE WHEN LOWER(SUBSTRING(p.name, 1, 1)) IN ('a','e','i','o','u') THEN 1 END) * 1.0) / COUNT(p.playerId)) DESC")
    List<CountryStartedVowelDTO> findCountriesByVowelStartPercentage();
}

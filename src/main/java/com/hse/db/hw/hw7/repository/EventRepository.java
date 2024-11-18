package com.hse.db.hw.hw7.repository;

import com.hse.db.hw.hw7.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, String> {

    // Task 2
    @Query("SELECT e FROM Event e "
            + "JOIN e.results r "
            + "WHERE e.isTeamEvent = 0 AND r.medal = 'GOLD' "
            + "GROUP BY e "
            + "HAVING COUNT(DISTINCT r.player) >= 2")
    List<Event> findIndividualEventsWithTieForGold();
}

package com.hse.db.hw.hw7.service;

import com.hse.db.hw.hw7.dto.BirthYearMedalStatsDTO;
import com.hse.db.hw.hw7.projection.BirthYearMedalStats;
import com.hse.db.hw.hw7.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServicde {

    @Autowired
    private ResultRepository resultRepository;

    public List<BirthYearMedalStatsDTO> getBirthYearMedalStatsFor2004Olympics() {
        List<BirthYearMedalStats> projections = resultRepository.findBirthYearMedalStatsFor2004Olympics();

        List<BirthYearMedalStatsDTO> stats = new ArrayList<>();
        for (BirthYearMedalStats projection : projections) {
            stats.add(new BirthYearMedalStatsDTO(
                    projection.getBirthYear(),
                    projection.getPlayerCount(),
                    projection.getGoldMedalCount()
            ));
        }

        return stats;
    }
}


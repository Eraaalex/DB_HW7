package com.hse.db.hw.hw7.repository;

import com.hse.db.hw.hw7.entity.Olympics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OlimpicsRepository extends CrudRepository<Olympics, String> {
}
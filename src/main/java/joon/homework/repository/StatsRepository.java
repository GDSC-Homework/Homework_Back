package joon.homework.repository;

import joon.homework.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    int countByUserIdAndCategoryAndCreatedAtBetween(Long userId, String category, LocalDateTime start, LocalDateTime end);
}

package joon.homework.repository;

import joon.homework.entity.MonthStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthStatsRepository extends JpaRepository<MonthStats, Long> {

    List<MonthStats> findAllByRoomId(Long roomId);
    List<MonthStats> findAllByRoomIdAndPassed(Long roomId, int passed);
}

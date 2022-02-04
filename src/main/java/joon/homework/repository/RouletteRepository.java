package joon.homework.repository;

import joon.homework.entity.Roulette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouletteRepository extends JpaRepository<Roulette, Long> {

    Roulette findByRoomId(Long roomId);
}

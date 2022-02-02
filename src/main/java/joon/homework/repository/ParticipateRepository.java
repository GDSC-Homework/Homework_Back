package joon.homework.repository;

import joon.homework.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {

    Participate findByUserId(Long userId);
    List<Participate> findAllByRoomId(Long roomId);
}

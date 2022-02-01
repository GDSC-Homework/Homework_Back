package joon.homework.repository;

import joon.homework.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {

    Participate findByUserId(Long userId);
}

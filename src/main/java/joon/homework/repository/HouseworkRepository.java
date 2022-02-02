package joon.homework.repository;

import joon.homework.entity.Housework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseworkRepository extends JpaRepository<Housework, Long> {

    List<Housework> findAllByUserId(Long userId);
    List<Housework> findAllByRoomId(Long roomId);
}

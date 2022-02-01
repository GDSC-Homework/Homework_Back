package joon.homework.repository;

import joon.homework.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByCode(String code);
}

package joon.homework.repository;

import joon.homework.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByRoomId(Long roomId);
}

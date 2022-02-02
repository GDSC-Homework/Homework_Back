package joon.homework.repository;

import joon.homework.entity.Housework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseworkRepository extends JpaRepository<Housework, Long> {
}

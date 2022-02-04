package joon.homework.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonthStats extends BaseEntity {

    @Column
    private Long roomId;

    @Column
    private int passed;

    @Column
    private String winner;

    @Column
    private Long userId;

    @Column
    private String userName;

    @Column
    private int count;

    @Builder
    public MonthStats(Long roomId, int passed, String winner, Long userId, String userName, int count) {
        this.roomId = roomId;
        this.passed = passed;
        this.winner = winner;
        this.userId = userId;
        this.userName = userName;
        this.count = count;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

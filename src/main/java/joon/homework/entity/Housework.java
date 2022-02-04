package joon.homework.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Housework extends BaseEntity {

    @Column(name = "housework")
    private String name;

    @Setter
    @Column
    private Long userId;

    @Column
    private Long roomId;

    @Column(name = "date")
    private String day;

    @Column
    private String startTime;

    @Column
    private String finishTime;

    @Column(name = "repeatOrNot")
    private Boolean repeat;

    @Column
    private int penalty;

    @Column
    private String memo;

    @Setter
    @Column
    private Boolean finished;

    @Builder
    public Housework(String name, Long userId, Long roomId, String day, String startTime, String finishTime, Boolean repeat, int penalty, String memo, Boolean finished) {
        this.name = name;
        this.userId = userId;
        this.roomId = roomId;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.repeat = repeat;
        this.penalty = penalty;
        this.memo = memo;
        this.finished = finished;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

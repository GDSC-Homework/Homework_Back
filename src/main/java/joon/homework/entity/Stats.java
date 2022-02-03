package joon.homework.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stats extends BaseEntity {

    @Column
    private Long roomId;

    @Column
    private Long userId;

    @Column
    @Setter
    private String category;

    @Builder
    public Stats(Long roomId, Long userId, String category) {
        this.roomId = roomId;
        this.userId = userId;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

package joon.homework.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank extends BaseEntity {

    @Column
    private Long roomId;

    @Setter
    @Column
    private int deposit;

    @Builder
    public Bank(Long roomId, int deposit) {
        this.roomId = roomId;
        this.deposit = deposit;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

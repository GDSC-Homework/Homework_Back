package joon.homework.dto.stats.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NameAndCount {

    private Long userId;
    private String name;
    private int count;

    @Builder
    public NameAndCount(Long userId, String name, int count) {
        this.userId = userId;
        this.name = name;
        this.count = count;
    }
}


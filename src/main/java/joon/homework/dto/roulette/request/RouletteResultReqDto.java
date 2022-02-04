package joon.homework.dto.roulette.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RouletteResultReqDto {

    private String token;
    private Long houseworkId;
    private Long userId;

    @Builder
    public RouletteResultReqDto(String token, Long houseworkId, Long userId) {
        this.token = token;
        this.houseworkId = houseworkId;
        this.userId = userId;
    }
}

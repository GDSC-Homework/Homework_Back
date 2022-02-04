package joon.homework.dto.stats.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMonthPassedMaxReqDto {

    private String token;

    @Builder
    public GetMonthPassedMaxReqDto(String token) {
        this.token = token;
    }
}

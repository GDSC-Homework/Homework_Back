package joon.homework.dto.stats.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMonthPassedReqDto {

    private String token;
    private int passed;

    @Builder
    public GetMonthPassedReqDto(String token, int passed) {
        this.token = token;
        this.passed = passed;
    }
}

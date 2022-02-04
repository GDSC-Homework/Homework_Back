package joon.homework.dto.stats.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMonthLatestStatsReqDto {

    private String token;
    private String category;

    @Builder
    public GetMonthLatestStatsReqDto(String token, String category) {
        this.token = token;
        this.category = category;
    }
}

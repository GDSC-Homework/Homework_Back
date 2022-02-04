package joon.homework.dto.stats.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMonthAllLatestStatsReqDto {

    private String token;
    private String category;

    @Builder
    public GetMonthAllLatestStatsReqDto(String token, String category) {
        this.token = token;
        this.category = category;
    }
}

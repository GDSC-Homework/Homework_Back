package joon.homework.dto.stats.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMonthAllLatestStatsReqDto {

    private String token;

    @Builder
    public GetMonthAllLatestStatsReqDto(String token) {
        this.token = token;
    }
}

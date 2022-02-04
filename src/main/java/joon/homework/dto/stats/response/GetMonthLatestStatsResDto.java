package joon.homework.dto.stats.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetMonthLatestStatsResDto {

    private List<String> winners;
    private List<NameAndCount> result;

    @Builder
    public GetMonthLatestStatsResDto(List<String> winners, List<NameAndCount> result) {
        this.winners = winners;
        this.result = result;
    }
}

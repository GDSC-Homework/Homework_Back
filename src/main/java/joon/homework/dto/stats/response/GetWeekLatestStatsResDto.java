package joon.homework.dto.stats.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetWeekLatestStatsResDto {

    private List<String> winners;
    private List<List<NameAndCount>> result;

    @Builder
    public GetWeekLatestStatsResDto(List<String> winners, List<List<NameAndCount>> result) {
        this.winners = winners;
        this.result = result;
    }
}

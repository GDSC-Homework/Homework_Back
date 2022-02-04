package joon.homework.dto.stats.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetMonthPassedResDto {

    private List<String> winners;
    private List<NameAndCount> result;

    @Builder
    public GetMonthPassedResDto(List<String> winners, List<NameAndCount> result) {
        this.winners = winners;
        this.result = result;
    }
}

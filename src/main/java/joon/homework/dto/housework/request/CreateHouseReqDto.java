package joon.homework.dto.housework.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateHouseReqDto {

    private String token;
    private String name;
    private List<Long> userId;
    private List<String> day;
    private String startTime;
    private String finishTime;
    private Boolean repeat;
    private int penalty;
    private String memo;

    @Builder
    public CreateHouseReqDto(String token, String name, List<Long> userId, List<String> day, String startTime, String finishTime, Boolean repeat, int penalty, String memo) {
        this.token = token;
        this.name = name;
        this.userId = userId;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.repeat = repeat;
        this.penalty = penalty;
        this.memo = memo;
    }
}

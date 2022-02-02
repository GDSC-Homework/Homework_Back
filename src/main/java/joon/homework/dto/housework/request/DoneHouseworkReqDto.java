package joon.homework.dto.housework.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DoneHouseworkReqDto {

    private String token;
    private Long houseworkId;

    @Builder
    public DoneHouseworkReqDto(String token, Long houseworkId) {
        this.token = token;
        this.houseworkId = houseworkId;
    }
}

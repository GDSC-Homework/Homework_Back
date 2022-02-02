package joon.homework.dto.housework.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMyHouseworkReqDto {

    private String token;

    @Builder
    public GetMyHouseworkReqDto(String token) {
        this.token = token;
    }
}

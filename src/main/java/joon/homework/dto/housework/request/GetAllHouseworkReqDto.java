package joon.homework.dto.housework.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllHouseworkReqDto {

    private String token;

    @Builder
    public GetAllHouseworkReqDto(String token) {
        this.token = token;
    }
}

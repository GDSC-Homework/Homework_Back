package joon.homework.dto.roulette.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoulettePriceReqDto {

    private String token;

    @Builder
    public RoulettePriceReqDto(String token) {
        this.token = token;
    }
}

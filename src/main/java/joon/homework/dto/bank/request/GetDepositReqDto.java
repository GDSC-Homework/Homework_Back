package joon.homework.dto.bank.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetDepositReqDto {

    private String token;

    @Builder
    public GetDepositReqDto(String token) {
        this.token = token;
    }
}

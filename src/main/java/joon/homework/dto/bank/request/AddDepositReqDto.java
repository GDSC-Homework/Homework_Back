package joon.homework.dto.bank.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddDepositReqDto {

    private String token;
    private int amount;

    @Builder
    public AddDepositReqDto(String token, int amount) {
        this.token = token;
        this.amount = amount;
    }
}

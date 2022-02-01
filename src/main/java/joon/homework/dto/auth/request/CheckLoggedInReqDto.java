package joon.homework.dto.auth.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckLoggedInReqDto {

    private String token;

    @Builder
    public CheckLoggedInReqDto(String token) {
        this.token = token;
    }
}

package joon.homework.dto.auth.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckLoggedInDto {

    private String token;

    @Builder
    public CheckLoggedInDto(String token) {
        this.token = token;
    }
}

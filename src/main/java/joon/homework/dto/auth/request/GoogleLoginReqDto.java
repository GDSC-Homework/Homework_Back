package joon.homework.dto.auth.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleLoginReqDto {

    private String idToken;

    @Builder
    public GoogleLoginReqDto(String idToken) {
        this.idToken = idToken;
    }
}

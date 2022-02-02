package joon.homework.dto.auth.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProfileReqDto {

    private String token;
    private Long userId;

    @Builder
    public GetProfileReqDto(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}

package joon.homework.dto.room.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetCodeReqDto {

    private String token;

    @Builder
    public GetCodeReqDto(String token) {
        this.token = token;
    }
}

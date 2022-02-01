package joon.homework.dto.room.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckRoomReqDto {

    private String token;

    @Builder
    public CheckRoomReqDto(String token) {
        this.token = token;
    }
}

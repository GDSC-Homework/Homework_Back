package joon.homework.dto.room.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRoomReqDto {

    private String token;

    @Builder
    public CreateRoomReqDto(String token) {
        this.token = token;
    }
}

package joon.homework.dto.room.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipateRoomReqDto {

    private String token;
    private String roomCode;

    @Builder
    public ParticipateRoomReqDto(String token, String roomCode) {
        this.token = token;
        this.roomCode = roomCode;
    }
}

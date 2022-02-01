package joon.homework.dto.room.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRoomResDto {

    private String roomCode;
    private Long roomId;

    @Builder
    public CreateRoomResDto(String roomCode, Long roomId) {
        this.roomCode = roomCode;
        this.roomId = roomId;
    }
}

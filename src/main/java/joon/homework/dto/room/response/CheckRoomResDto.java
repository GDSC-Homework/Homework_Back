package joon.homework.dto.room.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckRoomResDto {

    private Boolean result;
    private Long roomId;

    @Builder
    public CheckRoomResDto(Boolean result, Long roomId) {
        this.result = result;
        this.roomId = roomId;
    }
}

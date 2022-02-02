package joon.homework.dto.room.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetParticipantsReqDto {

    private String token;
    private Long roomId;

    @Builder
    public GetParticipantsReqDto(String token, Long roomId) {
        this.token = token;
        this.roomId = roomId;
    }
}

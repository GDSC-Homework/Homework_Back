package joon.homework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    private int status;
    private String message;
    private Object data;

    @Builder
    public ResponseDto(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

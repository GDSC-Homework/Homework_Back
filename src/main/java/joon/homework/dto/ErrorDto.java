package joon.homework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorDto {

    private int status;
    private String message;

    @Builder
    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
package joon.homework.exception;

public class InvalidRoomCodeException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 초대코드입니다.";

    public InvalidRoomCodeException() {
        super(MESSAGE);
    }

    public static String getErrorMessage() {
        return MESSAGE;
    }
}

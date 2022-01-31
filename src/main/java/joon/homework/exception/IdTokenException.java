package joon.homework.exception;

public class IdTokenException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 토큰입니다.";

    public IdTokenException() {
        super(MESSAGE);
    }

    public static String getErrorMessage() {
        return MESSAGE;
    }
}

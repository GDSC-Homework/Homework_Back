package joon.homework.exception;

public class NotLoggedInException extends RuntimeException {

    private static final String MESSAGE = "로그인되지 않았습니다.";

    public NotLoggedInException() {
        super(MESSAGE);
    }

    public static String getErrorMessage() {
        return MESSAGE;
    }
}

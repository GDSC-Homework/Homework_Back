package joon.homework.controller;

import joon.homework.dto.ErrorDto;
import joon.homework.exception.IdTokenException;
import joon.homework.exception.InvalidJwtException;
import joon.homework.exception.InvalidRoomCodeException;
import joon.homework.exception.NotLoggedInException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    //400
    @ExceptionHandler({
            IdTokenException.class,
            InvalidRoomCodeException.class
    })
    public ResponseEntity<ErrorDto> InvalidRequest(final RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(400).body(
                ErrorDto.builder()
                        .status(400)
                        .message(ex.getMessage())
                        .build()
        );
    }

    //401
    @ExceptionHandler({
            InvalidJwtException.class,
            NotLoggedInException.class
    })
    public ResponseEntity<ErrorDto> AuthException(final RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(401).body(
                ErrorDto.builder()
                        .status(401)
                        .message(ex.getMessage())
                        .build()
        );
    }

    //500
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorDto> HandleAllException(final Exception ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(500).body(
                ErrorDto.builder()
                        .status(500)
                        .message(ex.getMessage())
                        .build()
        );
    }
}

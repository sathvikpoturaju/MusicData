package com.sathvik.MusicData.util;

import com.sathvik.MusicData.exception.MusicDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.sathvik.MusicData.constants.ResponseMessages.*;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorInfo> validationExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);

        ErrorInfo errorInfo = new ErrorInfo();
        String errorMessage = null;
        HttpStatus httpStatus = null;

        if (e instanceof MethodArgumentNotValidException e1) {
            errorMessage = e1
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else if (e instanceof ConstraintViolationException e2) {
            errorMessage = e2
                    .getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else {
            errorMessage = GENERAL_ERROR;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setHttpStatus(httpStatus.value());

        return new ResponseEntity<>(errorInfo, httpStatus);
    }

    @ExceptionHandler(MusicDataException.class)
    public ResponseEntity<ErrorInfo> musicDataExceptionHandler(MusicDataException e) {
        log.error(e.getMessage(), e);

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(e.getMessage());

        HttpStatus httpStatus = switch(e.getMessage()) {
            case ARTIST_ID_NOT_FOUND,
                 ALBUM_ID_NOT_FOUND,
                 NO_ARTISTS_FOUND,
                 ALBUM_NAME_NOT_FOUND,
                 NO_ALBUMS_FOUND -> HttpStatus.NOT_FOUND;
            case ARTIST_NAME_EXISTS,
                 ALBUM_NAME_EXISTS -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        errorInfo.setHttpStatus(httpStatus.value());

        return new ResponseEntity<>(errorInfo, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(GENERAL_ERROR);
        errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package ru.nsu.bayramov.deliveryservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorModel> handleIllegalArgument(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ErrorModel(exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @RequiredArgsConstructor
    @Getter
    private final static class ErrorModel {
        private final String errorMessage;
    }
}

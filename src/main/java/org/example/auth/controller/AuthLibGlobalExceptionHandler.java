package org.example.auth.controller;

import org.example.auth.dto.ResponseObject;
import org.example.auth.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Этот класс ловит все исключения в приложении
 */
@ControllerAdvice
public class AuthLibGlobalExceptionHandler {

    /**
     * Ловит все исключения типа BaseException
     * @param exception исключение
     * @return ответ сервера
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseObject> handle(BaseException exception) {
        return new ResponseEntity<>(new ResponseObject(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}

package org.springproject.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springproject.controller.dto.ErrorResponse;
import org.springproject.exceptions.ExpCatAlreadyExistsException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpCatAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateExpCat(ExpCatAlreadyExistsException e){

        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode()).errorMessage(e.getMessage()).build());

    }
}

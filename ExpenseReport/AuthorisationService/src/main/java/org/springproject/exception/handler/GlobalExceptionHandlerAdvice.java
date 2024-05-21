package org.springproject.exception.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springproject.dto.ErrorResponse;
import org.springproject.exception.UserExistsException;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUserException(UserExistsException e){

        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                         ErrorResponse.builder()
                                 .errorCode(e.getErrorCode())
                                 .errorMessage(e.getMessage())
                                 .build()
                );


    }
}

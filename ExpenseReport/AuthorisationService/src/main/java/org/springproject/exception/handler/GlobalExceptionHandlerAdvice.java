package org.springproject.exception.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springproject.dto.ErrorResponse;
import org.springproject.exception.*;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){

        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );


    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e){

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );


    }

    @ExceptionHandler(InvalidAccessTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidAccessTokenException e){

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );


    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordsDoesNotMatchException(PasswordDoesNotMatchException e){

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );


    }
}

package org.springproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UserExistsException extends RuntimeException{

    String errorCode;

    public UserExistsException(String message,  String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

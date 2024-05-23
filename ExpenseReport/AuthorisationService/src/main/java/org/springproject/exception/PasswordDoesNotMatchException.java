package org.springproject.exception;

import lombok.Getter;

@Getter
public class PasswordDoesNotMatchException extends RuntimeException {

    private String errorCode;
    public PasswordDoesNotMatchException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

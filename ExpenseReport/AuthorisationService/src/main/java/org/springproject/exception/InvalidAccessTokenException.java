package org.springproject.exception;

import lombok.Getter;

@Getter
public class InvalidAccessTokenException extends RuntimeException {

    String errorCode;

    public InvalidAccessTokenException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}

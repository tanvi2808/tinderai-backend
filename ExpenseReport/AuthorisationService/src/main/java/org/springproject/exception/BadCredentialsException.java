package org.springproject.exception;

import lombok.Data;

@Data
public class BadCredentialsException extends RuntimeException {

    String errorCode;

    public BadCredentialsException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;


    }


}

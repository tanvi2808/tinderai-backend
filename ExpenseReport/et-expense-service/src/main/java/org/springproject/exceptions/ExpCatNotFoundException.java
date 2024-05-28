package org.springproject.exceptions;


import lombok.Getter;

@Getter
public class ExpCatNotFoundException extends RuntimeException{
    String errorCode;

    public ExpCatNotFoundException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;

    }
}

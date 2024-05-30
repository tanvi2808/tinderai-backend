package org.springproject.exceptions;


import lombok.Getter;

@Getter
public class ExpenseNotFoundException extends RuntimeException {

    String errorCode;
    public ExpenseNotFoundException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}

package org.springproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ExpCatAlreadyExistsException extends RuntimeException {

     String  errorCode;


    public ExpCatAlreadyExistsException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}

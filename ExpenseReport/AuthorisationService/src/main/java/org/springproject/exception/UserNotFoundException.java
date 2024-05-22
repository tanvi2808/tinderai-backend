package org.springproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserNotFoundException extends RuntimeException {

    String errorCode;

    public UserNotFoundException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }


}

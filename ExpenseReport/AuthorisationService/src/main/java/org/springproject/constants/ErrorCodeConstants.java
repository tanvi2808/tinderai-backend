package org.springproject.constants;


import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeConstants{

    USER_EXISTS_CODE("B409", "User already exists");

    final String errorCode;
    final String message;

}

package org.springproject.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeConstants{

    USER_EXISTS_CODE("B409", "User already exists"),
    USER_NOT_FOUND("B404", "User has not signed up yet"),
    BAD_CREDENTIALS("B401", "Invalid Credentials"),
    INVALID_ACCESS_TOKEN("T403", "Invalid Access Token");

    final String errorCode;
    final String message;

}

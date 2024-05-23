package org.springproject.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.SQLException;

@AllArgsConstructor
@Getter
public enum ErrorCodeConstants{

    USER_EXISTS_CODE("B409", "User already exists"),
    USER_NOT_FOUND("B404", "User has not signed up yet"),
    BAD_CREDENTIALS("B401", "Invalid Credentials"),
    INVALID_ACCESS_TOKEN("T403", "Invalid Access Token"),
    PASSWORD_DOES_NOT_MATCH("P406", "Old password does not match");

    final String errorCode;
    final String message;

}

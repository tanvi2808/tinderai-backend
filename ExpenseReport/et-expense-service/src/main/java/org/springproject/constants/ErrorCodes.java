package org.springproject.constants;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    USER_NOT_FOUND("B404","No such user found"),
    EXP_CAT_ALREADY_EXISTS("B406","This exception category already exists"),
    EXP_CAT_NOT_FOUND("B404"," exception category not found");


    final String errorCode;
    final String errorMessage;

}

package org.springproject.constants;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.JDBCException;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    USER_NOT_FOUND("B404","No such user found"),
    EXP_CAT_ALREADY_EXISTS("B406","This Expense category already exists"),
    EXP_CAT_NOT_FOUND("B404"," Expense Category not found"),
    EXPENSE_NOT_FOUND("B404"," Expense not found");


    final String errorCode;
    final String errorMessage;

}

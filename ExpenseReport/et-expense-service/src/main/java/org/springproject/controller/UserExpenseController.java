package org.springproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.constants.LoggingConstants;
import org.springproject.controller.dto.ExpCatRequest;
import org.springproject.data.model.ExpenseCategory;
import org.springproject.service.ExpCatService;

@RestController
@RequestMapping("/api/expenses")
@Slf4j
public class UserExpenseController {

    @Autowired
    ExpCatService expCatService;

    @PostMapping("{userId}/addNew")
    public ResponseEntity<ExpenseCategory> addNewExpenseCat(@PathVariable Long userId,
                                                            @RequestBody ExpCatRequest expCatRequest) {
        String methodName = "UserExpenseController:addNewExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId,expCatRequest.getExpCategory());


        ExpenseCategory expenseCategory =
                expCatService.addNewExpenseCat(userId, expCatRequest.getExpCategory());

        log.info(LoggingConstants.END_LOG_CONST, methodName);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseCategory);
    }

}


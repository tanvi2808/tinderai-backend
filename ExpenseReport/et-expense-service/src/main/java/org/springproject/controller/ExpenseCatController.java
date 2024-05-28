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

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@Slf4j
public class ExpenseCatController {

    @Autowired
    ExpCatService expCatService;

    @PostMapping("{userId}/categories/addNew")
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

    @GetMapping("/{userId}/categories/{expCatId}")
    public ResponseEntity<ExpenseCategory> getExpCatById(@PathVariable Long userId, @PathVariable Long expCatId){
        String methodName = "UserExpenseController:getExpCatById";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId,expCatId);


        ExpenseCategory expenseCategory =
                expCatService.getExpCatById(userId, expCatId);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId +":"+expCatId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseCategory);
    }

    @GetMapping("/{userId}/categories")
    public ResponseEntity<List<ExpenseCategory>> getAllExpCatByUserId(@PathVariable Long userId){
        String methodName = "UserExpenseController:getAllExpCatByUserId";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);


        var expenseCategories =
                expCatService.getAllExpCatByUserId(userId);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(expenseCategories);
    }

    @PutMapping("{userId}/categories/{expCatId}")
    public ResponseEntity<ExpenseCategory> updateExpenseCat(@PathVariable Long userId,
                                                            @PathVariable Long expCatId,
                                                            @RequestBody ExpCatRequest expCatRequest) {
        String methodName = "UserExpenseController:updateExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId,expCatRequest);


        ExpenseCategory expenseCategory =
                expCatService.updateExpenseCat(userId, expCatId, expCatRequest.getExpCategory());

        log.info(LoggingConstants.END_LOG_CONST, methodName);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseCategory);
    }

    @DeleteMapping("{userId}/categories/{expCatId}")
    public ResponseEntity<HttpStatus> deleteExpenseCat(@PathVariable Long userId,
                                                            @PathVariable Long expCatId) {
        String methodName = "UserExpenseController:deleteExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId,expCatId);


        expCatService.deleteExpenseCat(userId, expCatId);

        log.info(LoggingConstants.END_LOG_CONST, methodName);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}


package org.springproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.constants.LoggingConstants;
import org.springproject.controller.dto.ExpenseRequest;
import org.springproject.data.model.Expense;
import org.springproject.mapper.ExpenseHandlerMapper;
import org.springproject.service.ExpenseService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;
    @PostMapping("/{userId}/expenses")
    public ResponseEntity<Expense> addNewExpense(@PathVariable Long userId, @RequestBody  ExpenseRequest expenseRequest){
        String methodName = "ExpenseController:addNewExpense";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expenseRequest);

        var expense = expenseService.addNewExpense(userId, ExpenseHandlerMapper.INSTANCE.handleExpenseRequest(expenseRequest));
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId, expenseRequest);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expense);
    }


    @GetMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long userId, @PathVariable Long expenseId){
        String methodName = "ExpenseController:getExpenseById";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);

        Expense expense = expenseService.getExpenseById(userId, expenseId);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expense);
    }

    @GetMapping("/{userId}/expenses")
    public Page<Expense> getAllExpensesByUserId(@PathVariable Long userId,
                                                Pageable pageable,
                                                @RequestParam(required = false) String startDate,
                                                @RequestParam(required = false) String endDate){
        String methodName = "ExpenseController:getAllExpenses";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);

        var expenses = expenseService.getAllExpenses( userId, pageable, startDate, endDate);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return expenses;
    }

    @PutMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody ExpenseRequest expenseRequest){
        String methodName = "ExpenseController:updateExpense";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expenseId);
        var expense = expenseService.updateExpense(userId, expenseId, ExpenseHandlerMapper.INSTANCE.handleExpenseRequest(expenseRequest));
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expense);
    }

    @DeleteMapping("/{userId}/expenses/{expenseId}")
    public ResponseEntity<HttpStatus> deleteExpenseById(@PathVariable Long userId, @PathVariable Long expenseId){
        String methodName = "ExpenseController:deleteExpenseById";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);
        expenseService.deleteExpense(userId,expenseId);
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

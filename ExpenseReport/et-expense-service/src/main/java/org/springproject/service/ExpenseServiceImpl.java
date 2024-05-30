package org.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodes;
import org.springproject.constants.LoggingConstants;
import org.springproject.data.model.Expense;
import org.springproject.data.model.ExpenseCategory;
import org.springproject.data.repo.ExpCatRepo;
import org.springproject.data.repo.ExpenseRepo;
import org.springproject.exceptions.ExpCatNotFoundException;
import org.springproject.exceptions.ExpenseNotFoundException;
import org.springproject.service.model.ExpenseServiceRequest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepo expenseRepo;

    @Autowired
    ExpCatRepo expCatRepo;
    @Override
    public Expense addNewExpense(Long userId, ExpenseServiceRequest expenseServiceRequest) {
        String methodName = "ExpenseServiceImpl:addNewExpense";

        log.info(LoggingConstants.START_LOG_CONST, methodName,expenseServiceRequest);

        ExpenseCategory expenseCategory=  expCatRepo.findById(expenseServiceRequest.getExpenseCatId())
                .orElseThrow(()-> new ExpCatNotFoundException(ErrorCodes.EXP_CAT_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXP_CAT_NOT_FOUND.getErrorMessage()));

       Expense expense =  Expense.builder()
                .expenseCat(expenseCategory)
                .expenseTitle(expenseServiceRequest.getTitle())
                .amount(expenseServiceRequest.getAmount())
                .userId(userId)
                .createdAt(Instant.now())
                .build();

       Expense saved =  expenseRepo.save(expense);

        log.info(LoggingConstants.END_LOG_CONST, methodName, expenseServiceRequest);
        return saved;
    }

    @Override
    public Expense getExpenseById(Long userId, Long expenseId) {

        String methodName = "ExpenseServiceImpl:getExpenseById";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expenseId);

        Expense expense = expenseRepo.findByUserIdAndExpenseId(userId, expenseId)
                .orElseThrow(()-> new ExpenseNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXPENSE_NOT_FOUND.getErrorMessage()));

        log.info(LoggingConstants.END_LOG_CONST, methodName, expenseId);
        return expense;
    }

    @Override
    public Page<Expense> getAllExpenses(Long userId, Pageable pageable, String startDate, String endDate) {
        String methodName = "ExpenseServiceImpl:getAllExpenses";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);

        Page<Expense> expenses = null;
        if(startDate!=null && endDate != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Instant startTime = LocalDate.parse(startDate, dateTimeFormatter).atStartOfDay().toInstant(ZoneOffset.UTC);
            Instant endTime = LocalDate.parse(endDate, dateTimeFormatter).plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
             expenses = expenseRepo.findByUserIdAndCreatedAtBetween(userId, startTime, endTime, pageable);
        }
        else
             expenses = expenseRepo.findByUserId(userId, pageable);

        log.info(LoggingConstants.END_LOG_CONST, methodName);
        return expenses;
    }

    @Override
    public Expense updateExpense(Long userId, Long expenseId, ExpenseServiceRequest expenseServiceRequest) {
        String methodName = "ExpenseServiceImpl:updateExpense";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId,expenseId);
        var expense = expenseRepo.findByUserIdAndExpenseId(userId,expenseId).orElseThrow(()->
                new ExpenseNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXPENSE_NOT_FOUND.getErrorMessage()));

        ExpenseCategory expenseCategory=  expCatRepo.findById(expenseServiceRequest.getExpenseCatId())
                .orElseThrow(()-> new ExpCatNotFoundException(ErrorCodes.EXP_CAT_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXP_CAT_NOT_FOUND.getErrorMessage()));

        expense.setExpenseCat(expenseCategory);
        expense.setExpenseTitle(expenseServiceRequest.getTitle());
        expense.setAmount(expenseServiceRequest.getAmount());
        expense.setModifiedAt(Instant.now());

        var savedExpense = expenseRepo.save(expense);

        log.info(LoggingConstants.END_LOG_CONST, methodName);
        return savedExpense;
    }

    @Override
    public void deleteExpense(Long userId, Long expenseId) {
        String methodName = "ExpenseServiceImpl:deleteExpense";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expenseId);
        Expense expense = expenseRepo.findByUserIdAndExpenseId(userId, expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(ErrorCodes.EXPENSE_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXPENSE_NOT_FOUND.getErrorMessage()));
        expenseRepo.delete(expense);

        log.info(LoggingConstants.END_LOG_CONST, methodName);
        return ;

    }


}

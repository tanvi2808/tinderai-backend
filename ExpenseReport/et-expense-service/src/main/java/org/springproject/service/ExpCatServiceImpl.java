package org.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodes;
import org.springproject.constants.LoggingConstants;
import org.springproject.data.model.ExpenseCategory;
import org.springproject.data.repo.ExpCatRepo;
import org.springproject.exceptions.ExpCatAlreadyExistsException;
import org.springproject.exceptions.ExpCatNotFoundException;

import java.time.Instant;
import java.util.List;


@Service
@Slf4j
public class ExpCatServiceImpl implements ExpCatService {

    @Autowired
    ExpCatRepo expCatRepo;
    @Override
    public ExpenseCategory addNewExpenseCat(Long userId, String expCategory) {
        String methodName = "ExpCatServiceImpl:addNewExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expCategory);

        if(expCatRepo.existsByExpCatAndUserId(expCategory.toLowerCase(),
                userId)){
            throw new ExpCatAlreadyExistsException(ErrorCodes.EXP_CAT_ALREADY_EXISTS.getErrorCode()
                    ,ErrorCodes.EXP_CAT_ALREADY_EXISTS.getErrorMessage());
        }

        ExpenseCategory expenseCategory= ExpenseCategory
                                            .builder()
                                            .expCat(expCategory.toLowerCase())
                                            .userId(userId)
                                            .build();

        expCatRepo.save(expenseCategory);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return expenseCategory;
    }

    @Override
    public ExpenseCategory getExpCatById(Long userId, Long expCatId) {
        String methodName = "ExpCatServiceImpl:getExpCatById";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expCatId);

        ExpenseCategory expenseCategory =  expCatRepo.findByExpenseCatIdAndUserId(expCatId, userId)
                .orElseThrow(() -> new ExpCatNotFoundException(ErrorCodes.EXP_CAT_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXP_CAT_NOT_FOUND.getErrorMessage())
                );

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId +":"+ expCatId);
        return expenseCategory;

    }

    @Override
    public List<ExpenseCategory> getAllExpCatByUserId(Long userId) {
        String methodName = "ExpCatServiceImpl:getAllExpCatByUserId";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);

        var expenseCategories =  expCatRepo.findAllByUserId(userId);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return expenseCategories;
    }

    @Override
    public ExpenseCategory updateExpenseCat(Long userId, Long expCatId, String expCategory) {
        String methodName = "ExpCatServiceImpl:addNewExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expCategory);


        ExpenseCategory expenseCategory = expCatRepo.findByExpenseCatIdAndUserId(expCatId, userId)
                .orElseThrow(()-> new ExpCatNotFoundException(ErrorCodes.EXP_CAT_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXP_CAT_NOT_FOUND.getErrorMessage()));

        expenseCategory.setExpCat(expCategory.toLowerCase());
        expenseCategory.setModifiedAt(Instant.now());

        expCatRepo.save(expenseCategory);

        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return expenseCategory;
    }

    @Override
    public void deleteExpenseCat(Long userId, Long expCatId) {

        String methodName = "ExpCatServiceImpl:deleteExpenseCat";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId, expCatId);
        ExpenseCategory expenseCategory = expCatRepo.findById(expCatId)
                .orElseThrow(() -> new ExpCatNotFoundException(ErrorCodes.EXP_CAT_NOT_FOUND.getErrorCode(),
                        ErrorCodes.EXP_CAT_NOT_FOUND.getErrorMessage()));
        expCatRepo.delete(expenseCategory);
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId, expCatId);


    }
}

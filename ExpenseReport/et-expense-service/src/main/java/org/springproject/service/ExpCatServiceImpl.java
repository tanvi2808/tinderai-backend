package org.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.constants.ErrorCodes;
import org.springproject.controller.dto.ExpCatRequest;
import org.springproject.data.model.ExpenseCategory;
import org.springproject.data.repo.ExpCatRepo;
import org.springproject.exceptions.ExpCatAlreadyExistsException;
import org.springproject.service.model.ExpCatServiceRequest;


@Service
@Slf4j
public class ExpCatServiceImpl implements ExpCatService {

    @Autowired
    ExpCatRepo expCatRepo;
    @Override
    public ExpenseCategory addNewExpenseCat(Long userId, String expCategory) {
        // check is exists already

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


        return expenseCategory;
    }
}

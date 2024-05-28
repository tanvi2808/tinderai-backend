package org.springproject.service;

import org.springproject.data.model.ExpenseCategory;
import org.springproject.service.model.ExpCatServiceRequest;

import java.util.List;

public interface ExpCatService {

    public ExpenseCategory addNewExpenseCat(Long userId, String expCategory);

    ExpenseCategory getExpCatById(Long userId, Long expCatId);

    List<ExpenseCategory> getAllExpCatByUserId(Long userId);

    ExpenseCategory updateExpenseCat(Long userId, Long expCatId, String expCategory);

   void deleteExpenseCat(Long userId, Long expCatId);
}

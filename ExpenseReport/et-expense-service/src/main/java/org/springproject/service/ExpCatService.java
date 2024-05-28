package org.springproject.service;

import org.springproject.data.model.ExpenseCategory;
import org.springproject.service.model.ExpCatServiceRequest;

public interface ExpCatService {

    public ExpenseCategory addNewExpenseCat(Long userId, String expCategory);
}

package org.springproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springproject.data.model.Expense;
import org.springproject.service.model.ExpenseServiceRequest;

import java.util.List;

public interface ExpenseService {
    public Expense addNewExpense(Long userId, ExpenseServiceRequest expenseServiceRequest);

    Expense getExpenseById(Long userId, Long expenseId);

    Page<Expense> getAllExpenses(Long userId, Pageable pageable, String startDate, String endDate);

    Expense updateExpense(Long userId, Long expenseId, ExpenseServiceRequest expenseServiceRequest);

    void deleteExpense(Long userId, Long expenseId);
}

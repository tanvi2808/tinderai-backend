package org.springproject.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springproject.data.model.Expense;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    Optional<Expense> findByUserIdAndExpenseId(Long userId, Long expenseId);

    Page<Expense> findByUserIdAndCreatedAtBetween(Long userId, Instant startDate, Instant endDate, Pageable pageable);

    Page<Expense> findByUserId(Long userId, Pageable pageable);
}

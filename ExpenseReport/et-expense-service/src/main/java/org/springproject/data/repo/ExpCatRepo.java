package org.springproject.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springproject.data.model.ExpenseCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpCatRepo extends JpaRepository<ExpenseCategory, Long> {

    boolean existsByExpCatAndUserId(String expCat, Long userId);
    Optional<ExpenseCategory> findByExpenseCatIdAndUserId(Long expCatId, Long userId);


    List<ExpenseCategory> findAllByUserId(Long userId) ;

    boolean existsByExpenseCatIdAndUserId(Long expCatId, Long userId);
}

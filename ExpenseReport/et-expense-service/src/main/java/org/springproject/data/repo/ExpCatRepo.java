package org.springproject.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springproject.data.model.ExpenseCategory;

@Repository
public interface ExpCatRepo extends JpaRepository<ExpenseCategory, Long> {

    boolean existsByExpCatAndUserId(String expCat, Long userId);


}

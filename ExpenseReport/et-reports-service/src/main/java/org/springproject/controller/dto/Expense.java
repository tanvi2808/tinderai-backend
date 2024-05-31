package org.springproject.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    String expenseCategory;
    LocalDateTime createdAt;

    Double amount;
}

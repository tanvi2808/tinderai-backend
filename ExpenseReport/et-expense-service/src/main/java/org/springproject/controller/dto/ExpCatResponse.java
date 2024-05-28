package org.springproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpCatResponse {
    Long expenseId;
    String expCategory;
    Long userId;
    LocalDate createdAt;

}

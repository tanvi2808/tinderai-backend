package org.springproject.controller.service.model;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class MonthlyReport {

    private Map<String, Double> totalExpensesByCat;
    private Double totalExpenses;
}

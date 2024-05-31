package org.springproject.controller.service.model;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class WeeklyReport {

    private Map<String, Double> totalExpensesByDay;
    private Double totalExpenses;
}

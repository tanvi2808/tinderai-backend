package org.springproject.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springproject.constants.LoggingConstants;
import org.springproject.controller.dto.Expense;
import org.springproject.controller.dto.ReportRequest;
import org.springproject.controller.service.model.MonthlyReport;
import org.springproject.controller.service.model.WeeklyReport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Override
    public WeeklyReport getWeeklyReport(String userId, ReportRequest reportRequest) {

        String methodName = "ReportServiceImpl:getWeeklyReport";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);
        List<Expense> expenses = reportRequest.getExpenses();
        var totalExpensesByDate = expenses.stream()
                .collect(Collectors.groupingBy(expense ->
                        DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(expense.getCreatedAt()), Collectors.summingDouble(Expense::getAmount)));

        var totalExpenses = totalExpensesByDate.values().stream().mapToDouble(value -> value).sum();
        return WeeklyReport.builder()
                .totalExpensesByDay(totalExpensesByDate)
                .totalExpenses(totalExpenses)
                .build();

    }

    @Override
    public MonthlyReport getMonthlyReport(String userId, ReportRequest reportRequest) {
        String methodName = "ReportServiceImpl:getMonthlyReport";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);
        List<Expense> expenses = reportRequest.getExpenses();
        var totalExpensesByCat = expenses.stream()
                .collect(Collectors.groupingBy(Expense::getExpenseCategory, Collectors.summingDouble(Expense::getAmount)));

        var totalExpenses = totalExpensesByCat.values().stream().mapToDouble(value -> value).sum();
        return MonthlyReport.builder()
                .totalExpensesByCat(totalExpensesByCat)
                .totalExpenses(totalExpenses)
                .build();


    }


}

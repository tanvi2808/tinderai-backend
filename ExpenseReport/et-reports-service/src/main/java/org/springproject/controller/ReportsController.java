package org.springproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.constants.LoggingConstants;
import org.springproject.controller.dto.ReportRequest;
import org.springproject.controller.service.ReportService;
import org.springproject.controller.service.model.MonthlyReport;
import org.springproject.controller.service.model.WeeklyReport;

@RestController
@Slf4j
@RequestMapping("/api/users/{userId}/reports")
public class ReportsController {

    @Autowired
    ReportService reportService;

    @GetMapping("/weekly-report")
    public ResponseEntity<WeeklyReport> getWeeklyReport(@PathVariable String userId,
                                                        @RequestBody ReportRequest reportRequest){
        String methodName = "ReportController:getWeeklyReport";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);
        var weeklyReport = reportService.getWeeklyReport(userId, reportRequest);
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weeklyReport);

    }

    @GetMapping("/monthly-report")
    public ResponseEntity<MonthlyReport> getMonthlyReport(@PathVariable String userId,
                                                          @RequestBody ReportRequest reportRequest){
        String methodName = "ReportController:getMonthlyReport";
        log.info(LoggingConstants.START_LOG_CONST, methodName, userId);
        var montlyReport = reportService.getMonthlyReport(userId, reportRequest);
        log.info(LoggingConstants.END_LOG_CONST, methodName, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(montlyReport);

    }
}

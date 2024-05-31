package org.springproject.controller.service;

import org.springproject.controller.dto.ReportRequest;
import org.springproject.controller.service.model.MonthlyReport;
import org.springproject.controller.service.model.WeeklyReport;

public interface ReportService {
    public WeeklyReport getWeeklyReport(String userId, ReportRequest reportRequest) ;

    public MonthlyReport getMonthlyReport(String userId, ReportRequest reportRequest);
}

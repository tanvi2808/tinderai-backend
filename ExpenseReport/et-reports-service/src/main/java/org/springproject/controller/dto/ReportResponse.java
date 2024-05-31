package org.springproject.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springproject.controller.service.model.MonthlyReport;
import org.springproject.controller.service.model.WeeklyReport;

@Data
@Builder
public class ReportResponse {

    WeeklyReport weeklyReport;
    MonthlyReport monthlyReport;
}

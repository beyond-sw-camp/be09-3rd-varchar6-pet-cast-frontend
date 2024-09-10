package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getAllReports(int memberId);

    List<ReportDTO> getReportByReporterId(Integer reporterId, int memberId);

    List<ReportDTO> getReportByRespondentId(Integer respondentId, int memberId);
}

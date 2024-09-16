package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ReportService {
    Page<Map<String, Object>> getAllReports(String memberId, Pageable pageable) throws IllegalAccessException;

    List<ReportDTO> getReportByReporterId(Integer reporterId, String memberId) throws IllegalAccessException;

    List<ReportDTO> getReportByRespondentId(Integer respondentId, String memberId) throws IllegalAccessException;
}

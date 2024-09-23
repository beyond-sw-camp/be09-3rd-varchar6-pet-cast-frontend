package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="queryReportService")
public interface ReportService {
    Page<Map<String, Object>> getAllReports(int memberId, List<String> roles,Pageable pageable) throws IllegalAccessException;

    List<ReportDTO> getReportByReporterId(Integer reporterId, List<String> roles, int memberId) throws IllegalAccessException;

    List<ReportDTO> getReportByRespondentId(Integer respondentId, List<String> roles, int memberId) throws IllegalAccessException;
}

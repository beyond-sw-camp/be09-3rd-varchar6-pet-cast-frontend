package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceTests {
    @Autowired
    private ReportService reportService;


    @Test
    public void 신고_목록_조회() throws IllegalAccessException {

        String memberId = "3";

        List<ReportDTO> reportResponseDTOList = reportService.getAllReports(memberId);
        assertEquals(22, reportResponseDTOList.size());

    }

    @Test
    public void 신고자_검색() throws IllegalAccessException {
        int targetReporterId = 1;
        String memberId = "3";
        List<ReportDTO> reportResponseDTOList = reportService.getReportByReporterId(targetReporterId, memberId);

        for(ReportDTO report : reportResponseDTOList){
            assertEquals(report.getReporterId(), targetReporterId);
        }
    }

    @Test
    public void 피신고자_검색() throws IllegalAccessException {
        int targetRespondentId = 1;
        String memberId = "3";
        List<ReportDTO> reportRespondentDTOList = reportService.getReportByRespondentId(targetRespondentId, memberId);

        for(ReportDTO report : reportRespondentDTOList){
            assertEquals(report.getRespondentId(), targetRespondentId);
        }
    }

}
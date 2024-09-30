package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceTests {
    @Autowired
    private ReportService reportService;

    private Pageable pageable;

    @BeforeEach
    void setup(){
        /* 설명. set page default size*/
        pageable = PageRequest.of(0, 20);
    }

    @Test
    public void 신고_목록_조회() throws IllegalAccessException {

//        String memberId = "3";
        int memberId = 3;
        List<String> roles = new ArrayList<>();

        roles.add("ROLE_ADMIN");

//        List<ReportDTO> reportResponseDTOList = reportService.getAllReports(memberId, roles, pageable);
        Page<Map<String, Object>> responseReports = reportService.getAllReports(memberId, roles, pageable);

        assertEquals(22, responseReports.getTotalElements());
    }

    @Test
    public void 신고자_검색() throws IllegalAccessException {
        int targetReporterId = 1;
//        String memberId = "3";
        int memberId = 3;
        List<String> roles = new ArrayList<>();

        roles.add("ROLE_ADMIN");

        List<ReportDTO> reportResponseDTOList = reportService.getReportByReporterId(targetReporterId, roles, memberId);

        for(ReportDTO report : reportResponseDTOList){
            assertEquals(report.getReporterId(), targetReporterId);
        }
    }

    @Test
    public void 피신고자_검색() throws IllegalAccessException {
        int targetRespondentId = 1;
//        String memberId = "3";
        int memberId = 3;
        List<String> roles = new ArrayList<>();

        roles.add("ROLE_ADMIN");

        List<ReportDTO> reportRespondentDTOList = reportService.getReportByRespondentId(targetRespondentId, roles, memberId);

        for(ReportDTO report : reportRespondentDTOList){
            assertEquals(report.getRespondentId(), targetRespondentId);
        }
    }

}
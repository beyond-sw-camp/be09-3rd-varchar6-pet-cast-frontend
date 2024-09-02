package com.varchar6.petcast.domain.report.command.application.service;

import com.varchar6.petcast.domain.report.command.application.dto.request.ReportCreateRequestDTO;
import com.varchar6.petcast.domain.report.command.domain.aggregate.Report;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceTests {

    private final ReportService reportService;
    private static ReportCreateRequestDTO reportCreateRequestDTO = new ReportCreateRequestDTO();
    private final ModelMapper modelMapper;
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ReportServiceTests(ReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    @Test
    @Transactional
    public void 신고_작성_테스트(){
        reportCreateRequestDTO.setReason("뭐가 문제이죠?");
        reportCreateRequestDTO.setReporterId(4);
        reportCreateRequestDTO.setRespondentId(10);

        Report report = modelMapper.map(reportCreateRequestDTO, Report.class);
        report.setCreatedAt(LocalDateTime.now().format(FORMATTER));

        assertEquals("뭐가 문제이죠?",report.getReason());
    }
}
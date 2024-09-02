package com.varchar6.petcast.domain.report.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.report.command.application.dto.request.ReportCreateRequestDTO;
import com.varchar6.petcast.domain.report.command.application.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController(value = "commandReportController")
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createReport(@RequestBody ReportCreateRequestDTO reportCreateRequestDTO){

        int result = reportService.insertReport(reportCreateRequestDTO);

        if(result == 1)
            return ResponseEntity.ok(new ResponseMessage(201, "Report 생성 성공", null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(500, "Report 생성 실패", null));
    }
}

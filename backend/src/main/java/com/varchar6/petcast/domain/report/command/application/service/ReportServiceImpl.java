package com.varchar6.petcast.domain.report.command.application.service;

import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.domain.qna.command.domain.repository.QnaRepository;
import com.varchar6.petcast.domain.report.command.application.dto.request.ReportCreateRequestDTO;
import com.varchar6.petcast.domain.report.command.domain.aggregate.Report;
import com.varchar6.petcast.domain.report.command.domain.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service(value = "commandReportService")
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public int insertReport(ReportCreateRequestDTO reportCreateRequestDTO) {
        int result = 0;

        Report report = modelMapper.map(reportCreateRequestDTO, Report.class);
        report.setCreatedAt(LocalDateTime.now().format(FORMATTER));

        try {
            reportRepository.save(report);
            result++;
        }catch(Exception e){
            log.info("report 생성 실패");
        }

        return result;
    }
}

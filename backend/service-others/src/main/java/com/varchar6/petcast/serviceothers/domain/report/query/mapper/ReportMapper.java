package com.varchar6.petcast.serviceothers.domain.report.query.mapper;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    List<Map<String, Object>> selectAllReports(RequestList<?> requestList);

    List<ReportDTO> selectReportByReporterId(Integer reporterId);

    List<ReportDTO> selectReportByRespondentId(Integer respondentId);

    int selectAllReportCount();
}

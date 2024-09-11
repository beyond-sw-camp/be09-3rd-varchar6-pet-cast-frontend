package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import com.varchar6.petcast.serviceothers.domain.report.query.mapper.ReportMapper;
import com.varchar6.petcast.serviceothers.infrastructure.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service(value="queryReportService")
public class ReportServiceImpl implements ReportService{
    private final ReportMapper reportMapper;
    private final MemberService memberService;
    @Autowired
    public ReportServiceImpl(ReportMapper reportMapper, MemberService memberService) {
        this.reportMapper = reportMapper;
        this.memberService = memberService;
    }

    @Override
    @Transactional
    public List<ReportDTO> getAllReports(String memberId) throws IllegalAccessException {
        boolean flag = false;
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);

        checkRole(flag, map);

        return reportMapper.selectAllReports();
    }

    @Override
    @Transactional
    public List<ReportDTO> getReportByReporterId(Integer reporterId, String memberId) throws IllegalAccessException {
        boolean flag = false;
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);

        checkRole(flag, map);

        return reportMapper.selectReportByReporterId(reporterId);
    }

    @Override
    @Transactional
    public List<ReportDTO> getReportByRespondentId(Integer respondentId, String memberId) throws IllegalAccessException {
        boolean flag = false;
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);

        checkRole(flag, map);

        return reportMapper.selectReportByRespondentId(respondentId);
    }

    private void checkRole(boolean flag, Map<String, String> map) throws IllegalAccessException {
        List<String> RequestRoleList = memberService.checkMemberRole(map);

        for (String role : RequestRoleList) {
            if(role.equals("ROLE_ADMIN")) {
                flag = true;
                break;
            }
        }
        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");
    }
}

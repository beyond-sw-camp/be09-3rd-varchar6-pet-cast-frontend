package com.varchar6.petcast.serviceothers.domain.report.query.service;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.report.query.dto.ReportDTO;
import com.varchar6.petcast.serviceothers.domain.report.query.mapper.ReportMapper;
import com.varchar6.petcast.serviceothers.infrastructure.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<Map<String, Object>> getAllReports(int memberId, List<String> roles, Pageable pageable) throws IllegalAccessException {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", memberId);
//
//        checkRole(flag, map);

        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");

        RequestList<?> requestList = RequestList.builder()
//                .data()
                .pageable(pageable)
                .build();

        List<Map<String, Object>> content = reportMapper.selectAllReports(requestList);
        int total = reportMapper.selectAllReportCount();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    @Transactional
    public List<ReportDTO> getReportByReporterId(Integer reporterId, List<String> roles, int memberId) throws IllegalAccessException {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", memberId);
//
//        checkRole(flag, map);
        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");

        return reportMapper.selectReportByReporterId(reporterId);
    }

    @Override
    @Transactional
    public List<ReportDTO> getReportByRespondentId(Integer respondentId, List<String> roles,int memberId) throws IllegalAccessException {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", memberId);
//
//        checkRole(flag, map);
        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");

        return reportMapper.selectReportByRespondentId(respondentId);
    }

//    private void checkRole(boolean flag, Map<String, String> map) throws IllegalAccessException {
//        List<String> RequestRoleList = memberService.checkMemberRole(map);
//
//        for (String role : RequestRoleList) {
//            if(role.equals("ROLE_ADMIN")) {
//                flag = true;
//                break;
//            }
//        }
//        if(!flag)
//            throw new IllegalAccessException("관리자가 아닙니다.");
//    }
}

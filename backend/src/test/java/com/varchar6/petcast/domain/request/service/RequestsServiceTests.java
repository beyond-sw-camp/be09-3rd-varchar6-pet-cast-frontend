package com.varchar6.petcast.domain.request.service;

import com.varchar6.petcast.domain.request.command.application.service.RequestsService;
import com.varchar6.petcast.domain.request.dto.RequestRequestDTO;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RequestsServiceTests {

    @Autowired
    RequestsService requestsService;

    private static RequestRequestDTO requestRequestDTO;
    private static RequestResponseDTO requestResponseDTO;


    @Test
    public void 고객_요청서_목록_조회_테스트() {
        int memberId = 1;
        List<RequestResponseDTO> requestList = requestsService.findAllRequestsByMemberId(memberId);
        assertEquals(memberId, requestList.size());
    }
    @Test
    public void 업체_요청서_목록_조회_테스트() {
        int companyId = 1;
        List<RequestResponseDTO> requestList = requestsService.findAllRequestsByCompanyId(companyId);
        assertEquals(companyId, requestList.size());
    }
    @Test
    public void 요청서_상세_조회_테스트() {
        int requestId = 1;
        RequestResponseDTO request = requestsService.findRequestById(requestId);
        assertEquals(requestId, request);
    }
//    @Test
//    @Transactional
//    public void 요청서_작성_테스트() {
//        RequestRequestDTO.setContent("요청서 작성 테스트");
//        RequestRequestDTO.setCost(50000);
//        RequestRequestDTO.setLocation("수원");
//        RequestRequestDTO.setTime();
//        private String createdAt;
//        private String updatedAt;
//        private boolean active;
//    }

    @Test
    @Transactional
    public void 요청서_삭제_테스트() {

    }

    @Test
    @Transactional
    public void 요청서_수락_테스트() {

    }
    @Test
    @Transactional
    public void 요청서_거절_테스트() {

    }

}
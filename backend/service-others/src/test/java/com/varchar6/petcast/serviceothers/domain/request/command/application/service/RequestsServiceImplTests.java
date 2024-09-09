package com.varchar6.petcast.serviceothers.domain.request.command.application.service;

import com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.RequestsStatus;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.Requests;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.EventsRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestCategoryRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequestsServiceImplTests {

    @Autowired
    private RequestsServiceImpl requestsService;

    @Autowired
    private RequestsRepository requestsRepository;

    @Autowired
    private RequestCategoryRepository requestCategoryRepository;

    @Autowired
    private EventsRepository eventsRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Test
    @DisplayName("요청서 작성 테스트")
    @Transactional
    void createRequest() {
        CreateRequestsRequestDTO createRequestsRequestDTO = new CreateRequestsRequestDTO();
        createRequestsRequestDTO.setContent("Test Content");
        createRequestsRequestDTO.setHopeCost(5000);
        createRequestsRequestDTO.setHopeLocation("Test Location");
        createRequestsRequestDTO.setHopeTime(LocalDateTime.now().format(FORMATTER));
        createRequestsRequestDTO.setCompanyId(1);
        createRequestsRequestDTO.setCategoryId(List.of(1, 2));


        requestsService.createRequest(createRequestsRequestDTO);

        Requests createdRequest = requestsRepository.findAll().stream()
                .filter(request -> request.getContent().equals("Test Content"))
                .findFirst()
                .orElse(null);

        assertNotNull(createdRequest, "요청서가 성공적으로 작성되었습니다!");
        assertEquals(RequestsStatus.SENT, createdRequest.getStatus(), "업체의 응답을 기다리는 중입니다...");
//        assertTrue(requestCategoryRepository.existsByRequestId(createdRequest.getId()), "카테고리가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("요청서 삭제 테스트")
    @Transactional
    void deleteRequest() {

        int requestId = 1;
        requestsRepository.deleteById(requestId);

        Optional<Requests> deletedRequest = requestsRepository.findById(requestId);
        assertTrue(deletedRequest.isPresent(), "요청서 삭제가 완료되지 않았습니다...!");
        assertFalse(deletedRequest.get().isActive(), "요청서가 삭제되었습니다!");
    }

    @Test
    @DisplayName("요청서 수락 테스트")
    @Transactional
    void acceptRequest() {

        int requestId = 2;

        requestsService.acceptRequest(requestId);

        Requests acceptedRequest = requestsRepository.findById(requestId).orElseThrow();
        assertEquals(RequestsStatus.CONFIRMED, acceptedRequest.getStatus(), "요청서가 수락되었습니다!");
    }

    @Test
    @DisplayName("요청서 거절 테스트")
    @Transactional
    void rejectRequest() {

        int requestId = 2;

        requestsService.rejectRequest(requestId);

        Requests rejectedRequest = requestsRepository.findById(requestId).orElseThrow();
        assertEquals(RequestsStatus.REJECTED, rejectedRequest.getStatus(), "요청서가 거절되었습니다...");
    }
}
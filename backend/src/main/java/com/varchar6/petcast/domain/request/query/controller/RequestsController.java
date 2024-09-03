package com.varchar6.petcast.domain.request.query.controller;


import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.request.command.application.dto.response.CreateRequestsResponseDTO;
import com.varchar6.petcast.domain.request.command.application.service.RequestsService;
import com.varchar6.petcast.domain.request.query.mapper.RequestsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestsController {
    private final RequestsService requestsService;
    private RequestsMapper requestsMapper;

    @Autowired
    public RequestsController(RequestsService requestsService, RequestsMapper requestsMapper) {
        this.requestsService = requestsService;
        this.requestsMapper = requestsMapper;
    }


    // 고객 요청서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResponseMessage> findRequestsByCustomer(@PathVariable int memberId) {
        List<CreateRequestsResponseDTO> requests = requestsService.findAllRequestsByMemberId(memberId);
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                     .httpStatus(HttpStatus.OK.value())
                                                     .message("고객이 작성한 요청서 목록 조회 성공")
                                                     .result(requests)
                                                     .build());
    }

    // 업체 요청서 목록 조회
    @GetMapping("/list/{companyId}")
    public ResponseEntity<ResponseMessage> findRequestsForCompany(@PathVariable int companyId) {
        List<CreateRequestsResponseDTO> requests = requestsService.findAllRequestsByCompanyId(companyId);
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                  .httpStatus(HttpStatus.OK.value())
                                                  .message("업체가 받은 요청서 목록 조회 성공")
                                                  .result(requests)
                                                  .build());
    }

    // 요청서 상세 조회
    @GetMapping("/list/{requestId}")
    public ResponseEntity<ResponseMessage> getRequestById(@PathVariable int requestId) {
        CreateRequestsResponseDTO request = requestsService.findRequestById(requestId);
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("요청서 상세 조회 성공")
                        .result(request)
                        .build());
    }

}
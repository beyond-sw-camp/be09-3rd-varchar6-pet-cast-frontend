package com.varchar6.petcast.domain.request.controller;


import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.request.dto.RequestRequestDTO;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import com.varchar6.petcast.domain.request.repository.RequestMapper;
import com.varchar6.petcast.domain.request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    private final RequestService requestService;
    private RequestMapper requestMapper;

    @Autowired
    public RequestController(RequestService requestService, RequestMapper requestMapper) {
        this.requestService = requestService;
        this.requestMapper = requestMapper;
    }


    // 고객 요청서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResponseMessage> findRequestsByCustomer(@PathVariable int memberId) {
        List<RequestResponseDTO> requests = requestService.findAllRequestsByMemberId(memberId);
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
        List<RequestResponseDTO> requests = requestService.findAllRequestsByCompanyId(companyId);
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
        RequestResponseDTO request = requestService.findRequestById(requestId);
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("요청서 상세 조회 성공")
                        .result(request)
                        .build());
    }

    // 요청서 작성
    @PostMapping
    public ResponseEntity<ResponseMessage> createRequest(@RequestBody RequestRequestDTO requestRequestDTO) {
        requestService.createRequest(requestRequestDTO);
        return ResponseEntity
                .ok()
                .body(
                        RequestResponseDTO.builder()
                                .status(HttpStatus.CREATED.value())
                                .message("이벤트 생성 성공")
                                .result(null)
                                .build());
    }

    // 요청서 삭제
    @DeleteMapping("/list/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    // 요청서 수락
    @PutMapping("/list/{requestId}/accept")
    public ResponseEntity<RequestResponseDTO> acceptRequest(@PathVariable int requestId) {
        RequestResponseDTO updatedRequest = requestService.acceptRequest(requestId);
        return ResponseEntity.ok(updatedRequest);
    }

    // 요청서 거절
    @PutMapping("/list/{requestId}/reject")
    public ResponseEntity<RequestResponseDTO> rejectRequest(@PathVariable int requestId) {
        RequestResponseDTO updatedRequest = requestService.rejectRequest(requestId);
        return ResponseEntity.ok(updatedRequest);
    }
}
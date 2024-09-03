package com.varchar6.petcast.domain.request.command.application.controller;


import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.request.command.application.dto.RequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.application.dto.RequestsResponseDTO;

import com.varchar6.petcast.domain.request.query.mapper.RequestsMapper;
import com.varchar6.petcast.domain.request.command.application.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestsController {
    private final RequestsService requestsService;

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    // 요청서 작성
    @PostMapping
    public ResponseEntity<ResponseMessage> createRequest(@RequestBody RequestsRequestDTO requestsRequestDTO) {
        RequestsResponseDTO requestsResponseDTO = requestsService.createRequest(requestsRequestDTO);
        return ResponseEntity
                .ok()
                .body(
                        RequestsResponseDTO.builder()
                                .status(HttpStatus.CREATED.value())
                                .message("이벤트 생성 성공")
                                .result(null)
                                .build());
    }

    // 요청서 삭제
    @DeleteMapping("/list/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int requestId) {
        requestsService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    // 요청서 수락
    @PutMapping("/list/{requestId}/accept")
    public ResponseEntity<RequestsResponseDTO> acceptRequest(@PathVariable int requestId) {
        RequestsResponseDTO updatedRequest = requestsService.acceptRequest(requestId);
        return ResponseEntity.ok(updatedRequest);
    }

    // 요청서 거절
    @PutMapping("/list/{requestId}/reject")
    public ResponseEntity<RequestsResponseDTO> rejectRequest(@PathVariable int requestId) {
        RequestsResponseDTO updatedRequest = requestsService.rejectRequest(requestId);
        return ResponseEntity.ok(updatedRequest);
    }
}
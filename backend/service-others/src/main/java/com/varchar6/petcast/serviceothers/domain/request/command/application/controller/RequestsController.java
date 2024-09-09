package com.varchar6.petcast.serviceothers.domain.request.command.application.controller;


import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.serviceothers.domain.request.command.application.service.RequestsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController("commandRequestsController")
@RequestMapping("/api/v1/requests")
public class RequestsController {
    private final RequestsService requestsService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    // 요청서 작성
    @PostMapping("")
    public ResponseEntity<ResponseMessage> createRequest(
            @Valid @RequestBody CreateRequestsRequestDTO createRequestsRequestDTO) {

        try {
            int createRequest = requestsService.createRequest(createRequestsRequestDTO);
            return  ResponseEntity
                    .ok()
                    .body(
                            ResponseMessage.builder()
                                    .httpStatus(HttpStatus.CREATED.value())
                                    .message("요청서가 성공적으로 작성되었습니다!")
                                    .result(createRequest)
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            ResponseMessage.builder()
                                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                                    .message("요청서 작성 실패: 데이터가 올바르지 않습니다.")
                                    .result(-1)
                                    .build()
                    );
        }
    }

    // 요청서 삭제
    @DeleteMapping("/list/{requestId}")
    public ResponseEntity<ResponseMessage> deleteRequest(@PathVariable int requestId,
        @RequestHeader("X-Member-Id") String id) {

        int memberId = Integer.parseInt(id);

        String message = "요청서 삭제 성공!";
        try {
            requestsService.deleteRequest(requestId, memberId);
        } catch (Exception e) {
            message = "요청서 삭제 실패!";
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.NO_CONTENT.value())
                        .message(message)
                        .build()
        );
    }

    // 요청서 수락
    @PutMapping("/list/acceptance/{requestId}")
    public ResponseEntity<ResponseMessage> acceptRequest(@PathVariable int requestId,
        @RequestHeader("X-Member-Id") String id) {

        String message = "요청서 수락 성공!";
        try {
            requestsService.acceptRequest(requestId);
        } catch (Exception e) {
            message = "요청서 수락 실패!";
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.NO_CONTENT.value())
                        .message(message)
                        .build()
        );
    }

    // 요청서 거절
    @PutMapping("/list/refusal/{requestId}")
    public ResponseEntity<ResponseMessage> rejectRequest(@PathVariable int requestId,
        @RequestHeader("X-Member-Id") String id) {

        int memberId = Integer.parseInt(id);
        String message = "요청서 거절 성공!";

        try {
            requestsService.rejectRequest(requestId);
        } catch (Exception e) {
            message = "요청서 거절 실패!";
        }
        return ResponseEntity.ok(ResponseMessage.builder()
                .httpStatus(HttpStatus.NO_CONTENT.value())
                .message(message)
                .build()
        );
    }
}
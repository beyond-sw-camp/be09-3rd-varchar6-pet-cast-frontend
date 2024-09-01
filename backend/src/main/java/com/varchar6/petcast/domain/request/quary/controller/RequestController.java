package com.varchar6.petcast.domain.request.quary.controller;

import com.varchar6.petcast.domain.request.command.entity.Request;
import com.varchar6.petcast.domain.request.quary.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // 목록 조회
    @GetMapping("list")
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestService.findAllRequests());
    }

    // 상세 조회
    @GetMapping("list/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable int id) {
        return ResponseEntity.ok(requestService.findRequestById(id));
    }

    // 요청서 작성
    @PostMapping("list")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // 요청서 수정
    @PutMapping("list/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable int id, @RequestBody Request request) {
        Request updatedRequest = requestService.updateRequest(id, request);
        return ResponseEntity.ok(updatedRequest);
    }

    // 요청서 삭제
    @DeleteMapping("list/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}

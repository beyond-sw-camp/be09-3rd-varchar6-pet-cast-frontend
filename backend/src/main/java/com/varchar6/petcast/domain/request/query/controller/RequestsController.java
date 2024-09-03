package com.varchar6.petcast.domain.request.query.controller;


import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;
import com.varchar6.petcast.domain.request.query.mapper.RequestsMapper;
import com.varchar6.petcast.domain.request.query.service.RequestsService;
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

    @GetMapping("/categoryList")
    public ResponseEntity<ResponseMessage> findCategoryList(){
        String message = "카테고리 조회 성공!";
        List<String> categoryList = null;
        try {
            categoryList = requestsService.findCategoryList();
        }catch (Exception e){
            message = "카테고리 조회 실패!";
        }
        return ResponseEntity.ok(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(categoryList)
                .build());
    }

    // 고객 요청서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResponseMessage> findRequestsByCustomer(@PathVariable int memberId) {
        String message = "고객이 작성한 요청서 목록 조회 성공!";
        List<MemberAndRequestDTO> memberAndRequestDTO = null;
        try {
            memberAndRequestDTO = requestsService.findAllRequestsByMemberId(memberId);
        } catch (Exception e){
            message = "고객이 작성한 요청서 목록 조회 실패!";
        }
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                     .httpStatus(HttpStatus.OK.value())
                                                     .message(message)
                                                     .result(memberAndRequestDTO)
                                                     .build());
    }

    // 업체 요청서 목록 조회
    @GetMapping("/list/{companyId}")
    public ResponseEntity<ResponseMessage> findRequestsForCompany(@PathVariable int companyId) {
        String message = "업체가 받은 요청서 목록 조회 성공!";
        List<CompanyAndRequestDTO> companyAndRequestDTO = null;
        try{
            companyAndRequestDTO = requestsService.findAllRequestsByCompanyId(companyId);
        }catch (Exception e){
            message = "업체가 받은 요청서 목록 조회 실패!";
        }
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                  .httpStatus(HttpStatus.OK.value())
                                                  .message(message)
                                                  .result(companyAndRequestDTO)
                                                  .build());
    }

    // 요청서 상세 조회
    @GetMapping("/list/{requestId}")
    public ResponseEntity<ResponseMessage> getRequestById(@PathVariable int requestId) {
        String message = "요청서 상세 조회 성공!";
        RequestDetailDTO requestDetailDTO = null;
        try {
            requestDetailDTO = requestsService.findRequestById(requestId);
        } catch (Exception e){
            message = "요청서 상세 조회 실패!";
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(requestDetailDTO)
                        .build());
    }

}
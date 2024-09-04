package com.varchar6.petcast.domain.proposalandestimate.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estimates")
public class EstimateController {

    private final EstimateService estimateService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimateController ( EstimateService estimateService) {
        this.estimateService = estimateService;
    }


    // 고객 견적서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResponseMessage> findAllEstimatesByMemberId ( @PathVariable int memberId ) {
        List<EstimateResponseDTO> estimates
                = estimateService.findAllEstimatesByMemberId ( memberId );
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "고객 견적서 목록 조회 성공" )
                        .result ( estimates )
                        .build () );
    }

    // 업체 견적서 목록 조회
    @GetMapping("/list/{companyId}")
    public ResponseEntity<ResponseMessage> findAllEstimatesByCompanyId ( @PathVariable int companyId ) {
        List<EstimateResponseDTO> estimates = estimateService.findAllEstimatesByCompanyId ( companyId );
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "업체가 보낸 견적서 목록 조회 성공" )
                        .result ( estimates )
                        .build () );
    }

    // 요청서 상세 조회
    @GetMapping("/list/{requestId}")
    public ResponseEntity<ResponseMessage> findEstimateById ( @PathVariable int estimateId ) {
        EstimateResponseDTO estimate = estimateService.findEstimateById ( estimateId );
        return ResponseEntity.ok ()
                .body ( ResponseMessage.builder ()
                        .httpStatus ( HttpStatus.OK.value () )
                        .message ( "견적서 상세 조회 성공" )
                        .result ( estimate )
                        .build () );
    }

}


package com.varchar6.petcast.domain.proposalandestimate.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.service.EstimateService;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estimate")
public class EstimateController {
    private final EstimateService estimateService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimateController(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    // 고객 견적서 목록 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ResponseMessage> findAllEstimatesByMember(@PathVariable int memberId) {
        List<EstimateResponseDTO> estimates = estimateService.findAllEstimatesByMemberId(memberId);
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                .httpStatus(HttpStatus.OK.value())
                                                .message("기획서에 매핑된 견적서 목록 조회 성공")
                                                .result(estimates)
                                                .build());
    }

    // 업체 견적서 목록 조회
    @GetMapping("/{companyId}")
    public ResponseEntity<ResponseMessage> findAllEstimatesByCompany(@PathVariable int companyId) {
        List<EstimateResponseDTO> estimates = estimateService.findAllEstimatesByCompanyId(companyId);
        return ResponseEntity.ok().body(ResponseMessage.builder()
                                                        .httpStatus(HttpStatus.OK.value())
                                                        .message("업체가 작성한 견적서 목록 조회 성공")
                                                        .result(estimates)
                                                        .build());
                                    }

    // 견적서 상세 조회
    @GetMapping("/{estimateId}")
    public ResponseEntity<ResponseMessage> getEstimateById(@PathVariable int estimateId) {
        EstimateResponseDTO estimate = estimateService.findEstimateById(estimateId);
        return ResponseEntity.ok()
                             .body(ResponseMessage.builder()
                                                    .httpStatus(HttpStatus.OK.value())
                                                    .message("견적서 상세 조회 성공")
                                                    .result(estimate)
                                                    .build());
                    }

    // 견적서 작성
    @PostMapping("/create")
    public ResponseEntity<EstimateResponseDTO> createEstimate(@RequestBody EstimateRequestDTO estimateRequestDTO) {
        EstimateResponseDTO createEstimate = estimateService.createEstimate(estimateRequestDTO);
        return new ResponseEntity<>(createEstimate, HttpStatus.CREATED);
    }

    // 견적서 삭제
    @DeleteMapping("/delete/{estimateId}")
    public ResponseEntity<Void> deleteEstimate(@PathVariable int estimateId) {
        estimateService.deleteEstimate(estimateId);
        return ResponseEntity.noContent().build();
    }

    // 견적서 수락
    @PutMapping("/list/{estimateId}/accept")
    public ResponseEntity<EstimateResponseDTO> acceptEstimate(@PathVariable int estimateId) {
        EstimateResponseDTO updateEstimate = estimateService.acceptEstimate(estimateId);
        return ResponseEntity.ok(updateEstimate);
    }

    // 견적서 거절
    @PutMapping("/list/{estimateId}/reject")
    public ResponseEntity<EstimateResponseDTO> rejectEstimate(@PathVariable int estimateId) {
        EstimateResponseDTO updateEstimate = estimateService.rejectEstimate(estimateId);
        return ResponseEntity.ok(updateEstimate);
    }
}

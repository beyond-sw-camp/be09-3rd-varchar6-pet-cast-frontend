package com.varchar6.petcast.domain.proposalandestimate.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimateRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.service.EstimateService;
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


    // 견적서 작성
    @PostMapping("/create")
    public ResponseEntity<EstimatesResponseDTO> createEstimate(@RequestBody EstimateRequestDTO estimateRequestDTO) {
        EstimatesResponseDTO createEstimate = estimateService.createEstimate(estimateRequestDTO);
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
    public ResponseEntity<EstimatesResponseDTO> acceptEstimate(@PathVariable int estimateId) {
        EstimatesResponseDTO updateEstimate = estimateService.acceptEstimate(estimateId);
        return ResponseEntity.ok(updateEstimate);
    }

    // 견적서 거절
    @PutMapping("/list/{estimateId}/reject")
    public ResponseEntity<EstimatesResponseDTO> rejectEstimate(@PathVariable int estimateId) {
        EstimatesResponseDTO updateEstimate = estimateService.rejectEstimate(estimateId);
        return ResponseEntity.ok(updateEstimate);
    }
}

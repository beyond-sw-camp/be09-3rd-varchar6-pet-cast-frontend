package com.varchar6.petcast.domain.proposalandestimate.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.service.EstimatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/estimate")
public class EstimateController {
    private final EstimatesService estimatesService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimateController(EstimatesService estimatesService) {
        this.estimatesService = estimatesService;
    }


    // 견적서 작성
    @PostMapping("/create")
    public ResponseEntity<EstimatesResponseDTO> createEstimate(@RequestBody EstimatesRequestDTO estimatesRequestDTO) {
        EstimatesResponseDTO createEstimate = estimatesService.createEstimate(estimatesRequestDTO);
        return new ResponseEntity<>(createEstimate, HttpStatus.CREATED);
    }

    // 견적서 삭제
    @DeleteMapping("/delete/{estimateId}")
    public ResponseEntity<Void> deleteEstimate(@PathVariable int estimateId) {
        estimatesService.deleteEstimate(estimateId);
        return ResponseEntity.noContent().build();
    }

    // 견적서 수락
    @PutMapping("/list/{estimateId}/accept")
    public ResponseEntity<ResponseMessage> acceptEstimate(@PathVariable int estimateId) {
        estimatesService.acceptEstimate(estimateId);
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.ACCEPTED.value())
                        .message("견적서 수락!")
                        .result(null)
                        .build());
    }

    // 견적서 거절
    @PutMapping("/list/{estimateId}/reject")
    public ResponseEntity<ResponseMessage> rejectEstimate(@PathVariable int estimateId) {
        estimatesService.rejectEstimate(estimateId);
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("견적서 거절!")
                        .result(null)
                        .build());
    }
}

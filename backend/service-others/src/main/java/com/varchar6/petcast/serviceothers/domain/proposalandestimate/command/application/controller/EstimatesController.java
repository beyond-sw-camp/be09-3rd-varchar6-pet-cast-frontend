package com.varchar6.petcast.serviceothers.domain.proposalandestimate.command.application.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.serviceothers.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.serviceothers.domain.proposalandestimate.command.domain.repository.EstimatesRepository;
import com.varchar6.petcast.serviceothers.domain.proposalandestimate.command.service.EstimatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController("commandEstimatesController")
@RequestMapping("/api/v1/estimate")
public class EstimatesController {
    private final EstimatesService estimatesService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimatesController ( EstimatesService estimatesService) {
        this.estimatesService = estimatesService;
    }


    // 견적서 작성
    @PostMapping("")
    public ResponseEntity<ResponseMessage> createEstimate(@RequestBody EstimatesRequestDTO estimatesRequestDTO) {
        try{
        int createEstimate = estimatesService.createEstimate(estimatesRequestDTO);
        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("견적서가 성공적으로 작성되었습니다!")
                                .result(1)
                                .build()
                );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            ResponseMessage.builder()
                                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                                    .message("견적서 작성 실패: 데이터가 올바르지 않습니다.")
                                    .result(-1)
                                    .build()
                    );
        }
//        catch (Exception e) {
//            logger.error("견적서 생성 중 오류 발생", e);
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(
//                            ResponseMessage.builder()
//                                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                                    .message("견적서 작성 중 알 수 없는 오류가 발생했습니다.")
//                                    .result(-1)
//                                    .build()
//                    );
//        }
    }

    // 견적서 삭제
    @DeleteMapping("/{estimateId}")
    public ResponseEntity<ResponseMessage> deleteEstimate(@PathVariable int estimateId) {

        estimatesService.deleteEstimate(estimateId);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("견적서 삭제 성공")
                                .result(true)
                                .build());
    }

    // 견적서 수락
    @PutMapping("/list/accept/{estimateId}")
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
    @PutMapping("/list/reject/{estimateId}")
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

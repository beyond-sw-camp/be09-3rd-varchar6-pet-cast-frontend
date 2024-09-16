package com.varchar6.petcast.serviceothers.domain.qna.query.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import com.varchar6.petcast.serviceothers.domain.qna.query.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController(value="queryQnaController")
@RequestMapping("/api/v1/qna")
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @GetMapping("/company/{companyId}")
    private ResponseEntity<ResponseMessage> getQnaByCompanyId(@PathVariable Integer companyId,@PageableDefault(size = 20) Pageable pageable){

//        List<QnaDTO> responseQnas = qnaService.getQnaByCompanyId(companyId);
        Page<Map<String, Object>> responseQnas = qnaService.getQnaByCompanyId(companyId, pageable);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("업체에 달린 Q&A 조회 성공")
                                .result(responseQnas)
                                .build()
                );

    }

    @GetMapping("/questioner")
    private ResponseEntity<ResponseMessage> getQnaByQuestionerId(@RequestAttribute("memberId") int questionerId , @PageableDefault(size = 20) Pageable pageable){

//        List<QnaDTO> responseQnas = qnaService.getQnaByQuestionerId(questionerId);
        Page<Map<String, Object>> responseQnas = qnaService.getQnaByQuestionerId(questionerId, pageable);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("고객의 Q&A 조회 성공")
                                .result(responseQnas)
                                .build()
                );
    }

    @GetMapping("/{QnaId}")
    private ResponseEntity<ResponseMessage> getQnaById(@PathVariable Integer QnaId){

        QnaDTO responseQna = qnaService.getQnaById(QnaId);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("Q&A ID로 조회 성공")
                                .result(responseQna)
                                .build()
                );
    }
}

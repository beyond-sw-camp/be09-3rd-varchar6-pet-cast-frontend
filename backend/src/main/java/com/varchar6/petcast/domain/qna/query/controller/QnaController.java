package com.varchar6.petcast.domain.qna.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.qna.query.dto.QnaDTO;
import com.varchar6.petcast.domain.qna.query.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private ResponseEntity<ResponseMessage> getQnaByCompanyId(@PathVariable Integer companyId){

        List<QnaDTO> responseQnas = qnaService.getQnaByCompanyId(companyId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
                , responseQnas));
    }

    @GetMapping("/questioner/{questionerId}")
    private ResponseEntity<ResponseMessage> findEventByCustomer(@PathVariable Integer questionerId){

        List<QnaDTO> responseQnas = qnaService.getQnaByQuestionerId(questionerId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
                , responseQnas));
    }

    @GetMapping("/{QnaId}")
    private ResponseEntity<ResponseMessage> getQnaById(@PathVariable Integer QnaId){

        QnaDTO responseQna = qnaService.getQnaById(QnaId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
                , responseQna));
    }
}

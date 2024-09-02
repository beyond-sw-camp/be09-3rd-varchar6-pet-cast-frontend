package com.varchar6.petcast.domain.qna.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;
import com.varchar6.petcast.domain.qna.command.application.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController(value = "commandQnaController")
@RequestMapping("/api/v1/qna")
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createQna(@RequestBody QnaCreateRequestDTO qnaCreateRequestDTO){

        int result = qnaService.insertQna(qnaCreateRequestDTO);

        if(result == 1)
            return ResponseEntity.ok(new ResponseMessage(201, "Q&A 생성 성공", null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(500, "Q&A 생성 실패", null));
    }

    @PutMapping("")
    private ResponseEntity<ResponseMessage> updateAnswer(@RequestBody QnaUpdateRequestDTO qnaUpdateRequestDTO){

        QnaResponseDTO qnaResponseDTO = qnaService.updateQna(qnaUpdateRequestDTO);

        return ResponseEntity.ok(new ResponseMessage(201, "Q&A 수정 성공"
                , qnaResponseDTO));
    }

    @DeleteMapping("")
    private ResponseEntity<ResponseMessage> setQnaActive(@RequestBody Map<String, Integer> request){
        int id = request.get("id");
        int result = qnaService.setQnaActive(id);

        if(result == 1)
            return ResponseEntity.ok(new ResponseMessage(201, "Q&A 삭제 성공", null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(500, "Q&A 삭제 실패", null));
    }
    @PutMapping("/answer")
    private ResponseEntity<ResponseMessage> deleteQnaAnswer(@RequestBody QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO){

        QnaResponseDTO qnaResponseDTO = qnaService.deleteQnaAnswer(qnaDeleteAnswerRequestDTO);

        return ResponseEntity.ok(new ResponseMessage(201, "Q&A 수정 성공"
                , qnaResponseDTO));
    }
}

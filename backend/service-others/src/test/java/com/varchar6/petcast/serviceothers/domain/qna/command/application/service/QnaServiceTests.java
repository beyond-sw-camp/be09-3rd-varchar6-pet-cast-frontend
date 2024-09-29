package com.varchar6.petcast.serviceothers.domain.qna.command.application.service;

import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    private final QnaService qnaService;

    private static QnaCreateRequestDTO qnaCreateRequestDTO = new QnaCreateRequestDTO();
    private static QnaUpdateRequestDTO qnaUpdateRequestDTO = new QnaUpdateRequestDTO();
    private static QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO = new QnaDeleteAnswerRequestDTO();

    @Autowired
    public QnaServiceTests(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @Test
    @Transactional
    public void 질문_작성_테스트(){
        qnaCreateRequestDTO.setTitle("뭐가 문제인건가요??");
        qnaCreateRequestDTO.setContent("흠...");
        qnaCreateRequestDTO.setQuestionerId(8);
        qnaCreateRequestDTO.setCompanyId(7);


        int result = qnaService.insertQna(qnaCreateRequestDTO);
        assertEquals(1,result);
    }

    @Test
    @Transactional
    public void 답변_작성_테스트(){
        int result;
        qnaUpdateRequestDTO.setId(32);
        qnaUpdateRequestDTO.setAnswer("저희도 모릅니다");
        qnaUpdateRequestDTO.setAnswererId(3);

        result = qnaService.updateQna(qnaUpdateRequestDTO);

        assertEquals(result, 1);
    }

    @Test
    @Transactional
    public void 답변_삭제_테스트(){
        qnaDeleteAnswerRequestDTO.setId(38);
        qnaDeleteAnswerRequestDTO.setCompanyId(30);
        qnaDeleteAnswerRequestDTO.setAnswererId(6);

        int result = qnaService.deleteQnaAnswer(qnaDeleteAnswerRequestDTO);

        assertEquals(result, 1);
    }

    @Test
    @Transactional
    public void 질문_답변_비활성화_테스트(){
        int result;
        result = qnaService.setQnaActive(22,32);

        assertEquals(1,result);
    }
}
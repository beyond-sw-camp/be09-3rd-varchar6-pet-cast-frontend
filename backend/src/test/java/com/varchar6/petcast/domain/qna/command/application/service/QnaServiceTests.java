package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaSetActiveRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    private final QnaService qnaService;

    private static QnaCreateRequestDTO qnaCreateRequestDTO = new QnaCreateRequestDTO();
    private static QnaSetActiveRequestDTO qnaSetActiveRequestDTO = new QnaSetActiveRequestDTO();
    private static QnaUpdateRequestDTO qnaUpdateRequestDTO = new QnaUpdateRequestDTO();
    private final ModelMapper modelMapper;
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);


    @Autowired
    public QnaServiceTests(ModelMapper modelMapper, QnaService qnaService) {
        this.modelMapper = modelMapper;
        this.qnaService = qnaService;
    }

    @Test
    @Transactional
    public void 질문_작성_테스트(){
        qnaCreateRequestDTO.setTitle("뭐가 문제이죠?");
        qnaCreateRequestDTO.setContent("잘 모르겠습니다");
        qnaCreateRequestDTO.setQuestionerId(8);

        Qna qna = modelMapper.map(qnaCreateRequestDTO, Qna.class);
        qna.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        qna.setActive(true);
        qna.setAnswered(false);

        assertEquals("뭐가 문제이죠?",qna.getTitle());
    }
}
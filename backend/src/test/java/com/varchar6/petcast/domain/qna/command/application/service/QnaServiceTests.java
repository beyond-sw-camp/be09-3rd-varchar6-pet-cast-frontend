package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.domain.qna.command.domain.repository.QnaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    private final QnaService qnaService;

    private static QnaCreateRequestDTO qnaCreateRequestDTO = new QnaCreateRequestDTO();
    private static QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO = new QnaDeleteAnswerRequestDTO();
    private static QnaUpdateRequestDTO qnaUpdateRequestDTO = new QnaUpdateRequestDTO();
    private final ModelMapper modelMapper;
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
    @Qualifier("qnaRepository")
    @Autowired
    private QnaRepository qnaRepository;


    @Autowired
    public QnaServiceTests(ModelMapper modelMapper, QnaService qnaService) {
        this.modelMapper = modelMapper;
        this.qnaService = qnaService;
    }

    @Test
    @Transactional
    public void 질문_작성_테스트(){
        qnaCreateRequestDTO.setTitle("뭐가 문제인건가요??");
        qnaCreateRequestDTO.setContent("흠...");
        qnaCreateRequestDTO.setQuestionerId(8);

        Qna qna = modelMapper.map(qnaCreateRequestDTO, Qna.class);
        qna.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        qna.setActive(true);
        qna.setAnswered(false);

        assertEquals("뭐가 문제인건가요??",qna.getTitle());
    }

    @Test
    @Transactional
    public void 답변_작성_테스트(){
        qnaUpdateRequestDTO.setId(23);
        qnaUpdateRequestDTO.setAnswer("저희도 모릅니다");
        qnaUpdateRequestDTO.setCompanyId(8);
        qnaUpdateRequestDTO.setAnswererId(3);


        Qna qna = modelMapper.map(qnaUpdateRequestDTO, Qna.class);
        qna.setAnsweredAt(LocalDateTime.now().format(FORMATTER));
        qna.setAnswered(true);

        assertEquals(23,qna.getId());
    }

    @Test
    @Transactional
    public void 답변_삭제_테스트(){
        qnaUpdateRequestDTO.setId(23);
        qnaUpdateRequestDTO.setCompanyId(8);
        qnaUpdateRequestDTO.setAnswererId(3);


        Qna qna = modelMapper.map(qnaUpdateRequestDTO, Qna.class);
        qna.setAnswer("");

        assertEquals("",qna.getAnswer());
    }

    @Test
    @Transactional
    public void 답변_비활성화_테스트(){

        qnaService.setQnaActive(22);

        assertEquals(Optional.empty(),qnaRepository.findById(22));

    }
}
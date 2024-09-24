package com.varchar6.petcast.serviceothers.domain.qna.query.service;

import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import com.varchar6.petcast.serviceothers.domain.qna.query.mapper.QnaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    @Autowired
    private QnaService qnaService;

    private Pageable pageable;

//    public QnaServiceTests(QnaService qnaService) {
//        this.qnaService = qnaService;
//    }

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(0, 20);
    }

    @Test
    public void 고객_본인이_작성한_QnA_조회(){
        int targetQuestionerId = 7;
//        List<QnaDTO> QnaDTOList = qnaService.getQnaByQuestionerId(targetQuestionerId);
        Page<Map<String, Object>> responseQnas = qnaService.getQnaByCompanyId(targetQuestionerId, pageable);

        assertTrue(responseQnas.isFirst());
    }

    @Test
    public void 업체에_달린_QnA_조회(){
        Integer targetCompanyId = 1;
//        List<QnaDTO> QnaDTOList = qnaService.getQnaByCompanyId(targetCompanyId, pageable);
        Page<Map<String, Object>> responseQnas = qnaService.getQnaByCompanyId(targetCompanyId, pageable);

        assertTrue(responseQnas.isFirst());
    }

    @Test
    public void QnA_상세_조회(){
        int targetId = 1;
        QnaDTO qnaDTO = qnaService.getQnaById(targetId);

        assertEquals(qnaDTO.getId(), targetId);
    }

}
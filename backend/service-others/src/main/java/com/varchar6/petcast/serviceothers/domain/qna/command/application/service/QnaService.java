package com.varchar6.petcast.serviceothers.domain.qna.command.application.service;

import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;

public interface QnaService {
    int insertQna(QnaCreateRequestDTO qnaCreateRequestDTO);
    int updateQna(QnaUpdateRequestDTO qnaUpdateRequestDTO);
    int setQnaActive(int id, int memberId);
    int deleteQnaAnswer(QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO);
}

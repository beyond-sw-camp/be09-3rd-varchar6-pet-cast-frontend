package com.varchar6.petcast.serviceothers.domain.qna.query.service;

import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface QnaService {
    Page<Map<String, Object>> getQnaByQuestionerId(Integer questionerId, Pageable pageable);

    Page<Map<String, Object>> getQnaByCompanyId(Integer companyId, Pageable pageable);

    QnaDTO getQnaById(Integer id);
}

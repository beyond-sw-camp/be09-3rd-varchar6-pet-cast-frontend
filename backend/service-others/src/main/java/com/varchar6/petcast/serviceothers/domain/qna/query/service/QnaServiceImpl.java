package com.varchar6.petcast.serviceothers.domain.qna.query.service;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import com.varchar6.petcast.serviceothers.domain.qna.query.mapper.QnaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Slf4j
@Service(value="queryQnaService")
public class QnaServiceImpl implements QnaService{
    private final QnaMapper qnaMapper;

    @Autowired
    public QnaServiceImpl(QnaMapper qnaMapper) {
        this.qnaMapper = qnaMapper;
    }

    @Override
    @Transactional
    public Page<Map<String, Object>> getQnaByQuestionerId(Integer questionerId, Pageable pageable){

        RequestList<?> requestList = RequestList.builder()
                .data(questionerId)
                .pageable(pageable)
                .build();

        List<Map<String, Object>> content = qnaMapper.selectQnaByQuestionerId(requestList);
        int total = qnaMapper.selectQnaByQuestionerIdCount(requestList);

        return new PageImpl<>(content, pageable, total);
//        return qnaMapper.selectQnaByQuestionerId(questionerId);
    }

    @Override
    @Transactional
    public Page<Map<String, Object>> getQnaByCompanyId(Integer companyId, Pageable pageable){
        RequestList<?> requestList = RequestList.builder()
                .data(companyId)
                .pageable(pageable)
                .build();

        List<Map<String, Object>> content = qnaMapper.selectQnaByCompanyId(requestList);
        int total = qnaMapper.selectQnaByCompanyIdCount(requestList);

        return new PageImpl<>(content, pageable, total);
//        return qnaMapper.selectQnaByCompanyId(companyId);
    }

    @Override
    @Transactional
    public QnaDTO getQnaById(Integer id){
        return qnaMapper.selectQnaById(id);
    }
}

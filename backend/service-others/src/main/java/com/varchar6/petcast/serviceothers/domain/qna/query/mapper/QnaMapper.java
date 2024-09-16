package com.varchar6.petcast.serviceothers.domain.qna.query.mapper;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {
    List<Map<String, Object>> selectQnaByQuestionerId(RequestList<?> requestList);

    List<Map<String, Object>> selectQnaByCompanyId(RequestList<?> requestList);

    QnaDTO selectQnaById(Integer id);

    int selectQnaByQuestionerIdCount(RequestList<?> requestList);

    int selectQnaByCompanyIdCount(RequestList<?> requestList);
}

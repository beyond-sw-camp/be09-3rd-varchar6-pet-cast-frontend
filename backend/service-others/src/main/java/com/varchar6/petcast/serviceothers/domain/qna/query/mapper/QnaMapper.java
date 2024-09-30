package com.varchar6.petcast.serviceothers.domain.qna.query.mapper;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.qna.query.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {
    List<Map<String, Object>> selectQnaByQuestionerId(Map<String, Object> params);

//    List<Map<String, Object>> selectQnaByCompanyId(RequestList<?> requestList);
    List<Map<String, Object>> selectQnaByCompanyId(Map<String, Object> params);

    QnaDTO selectQnaById(Integer id);

    int selectQnaByQuestionerIdCount(Map<String, Object> params);

//    int selectQnaByCompanyIdCount(RequestList<?> requestList);
    int selectQnaByCompanyIdCount(Map<String, Object> params);
}

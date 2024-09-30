package com.varchar6.petcast.serviceothers.domain.notice.query.mapper;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.notice.query.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {
    List<Map<String, Object>> selectAllNotice(RequestList<?> requestList);
    NoticeDTO selectNoticeByNoticeId(int noticeId);
    int selectAllNoticeCount();
}

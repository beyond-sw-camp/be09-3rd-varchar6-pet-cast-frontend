package com.varchar6.petcast.serviceothers.domain.notice.query.service;

import com.varchar6.petcast.serviceothers.common.util.RequestList;
import com.varchar6.petcast.serviceothers.domain.notice.query.dto.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NoticeService {

    NoticeDTO getNoticeById(int memberId);
//    Page<Map<String, Object>> getAllNotices(NoticeDTO noticeDTO, Pageable pageable);
    Page<Map<String, Object>> getAllNotices(NoticeDTO noticeDTO, Pageable pageable);
}

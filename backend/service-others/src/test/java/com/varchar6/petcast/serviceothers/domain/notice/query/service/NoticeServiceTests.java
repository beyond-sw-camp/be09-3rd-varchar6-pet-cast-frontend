package com.varchar6.petcast.serviceothers.domain.notice.query.service;

import com.varchar6.petcast.serviceothers.domain.notice.query.dto.NoticeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTests {

    private final NoticeService noticeService;
    private final NoticeDTO noticeDTO;
    private final Pageable pageable;

    @Autowired
    public NoticeServiceTests(NoticeService noticeService, NoticeDTO noticeDTO, Pageable pageable) {
        this.noticeService = noticeService;
        this.noticeDTO = noticeDTO;
        this.pageable = pageable;
    }

    @Test
    public void 공지_코드로_공지_찾기() {
        int targetNoticeId = 1;
        NoticeDTO noticeDTO = noticeService.getNoticeById(targetNoticeId);

        assertEquals(noticeDTO.getId(), targetNoticeId);
    }

    @Test
    public void 모든_공지_조회() {
        Page<Map<String, Object>> responseNotices = noticeService.getAllNotices(noticeDTO, pageable);
        assertNotNull(responseNotices);
    }

}
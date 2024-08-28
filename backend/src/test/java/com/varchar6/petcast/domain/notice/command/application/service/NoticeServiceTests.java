package com.varchar6.petcast.domain.notice.command.application.service;

import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTests {

    @Autowired
    NoticeService noticeService;

    private static NoticeWriteRequestDTO noticeWriteRequestDTO = new NoticeWriteRequestDTO();

    @Test
    public void 공지_생성_테스트() {
        noticeWriteRequestDTO.setTitle("testTitle");
        noticeWriteRequestDTO.setMemberNoticeId(1);
        noticeWriteRequestDTO.setDescription("testDescription");
        noticeWriteRequestDTO.setTopFix(true);
        NoticeResponseDTO noticeResponseDTO = noticeService.createNotice(noticeWriteRequestDTO);

        assertEquals(0, noticeResponseDTO.getView());
        assertEquals("testTitle", noticeResponseDTO.getTitle());
        assertEquals(1, noticeResponseDTO.getMemberNoticeId());
        assertEquals("testDescription", noticeResponseDTO.getDescription());
        assertTrue(noticeResponseDTO.isTopFix());

    }
}
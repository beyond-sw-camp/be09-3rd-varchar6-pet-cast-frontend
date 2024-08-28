package com.varchar6.petcast.domain.notice.command.application.service;

import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTests {

    @Autowired
    NoticeService noticeService;

    private static NoticeWriteRequestDTO noticeWriteRequestDTO = new NoticeWriteRequestDTO();
    private static NoticeUpdateRequestDTO noticeUpdateRequestDTO = new NoticeUpdateRequestDTO();

    @Test
    @Transactional
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


    @Test
    @Transactional
    public void 공지_수정_테스트() {
        noticeUpdateRequestDTO.setId(1);
        noticeUpdateRequestDTO.setTitle("testModifiedTitle");
        noticeUpdateRequestDTO.setDescription("testModifiedDescription");
        noticeUpdateRequestDTO.setTopFix(false);
        NoticeResponseDTO noticeResponseDTO = noticeService.updateNotice(noticeUpdateRequestDTO);

        assertEquals(1, noticeResponseDTO.getId());
        assertEquals(0, noticeResponseDTO.getView());
        assertEquals("testModifiedTitle", noticeResponseDTO.getTitle());
        assertEquals("testModifiedDescription", noticeResponseDTO.getDescription());
        assertFalse(noticeResponseDTO.isTopFix());
        assertNotEquals(noticeResponseDTO.getCreatedAt(), noticeResponseDTO.getUpdatedAt());

    }
}
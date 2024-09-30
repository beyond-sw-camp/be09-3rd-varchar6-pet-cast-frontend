package com.varchar6.petcast.serviceothers.domain.notice.command.application.service;

import com.varchar6.petcast.serviceothers.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NoticeService {
    int insertNotice(NoticeWriteRequestDTO noticeWriteRequestDTO, List<String> roles) throws IllegalAccessException;
    int updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO, List<String> roles) throws IllegalAccessException;
    int deleteNotice(int noticeId, int memberId, List<String> roles) throws IllegalAccessException;

}

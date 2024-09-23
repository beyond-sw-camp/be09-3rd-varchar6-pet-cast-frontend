package com.varchar6.petcast.serviceothers.domain.notice.command.application.service;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.serviceothers.domain.notice.command.domain.aggregate.Notice;
import com.varchar6.petcast.serviceothers.domain.notice.command.domain.repository.NoticeRepository;
import com.varchar6.petcast.serviceothers.infrastructure.client.MemberServiceClient;
import com.varchar6.petcast.serviceothers.infrastructure.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service(value = "commandNoticeService")
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;
    MemberServiceClient memberServiceClient;
    private final MemberService memberService;
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, ModelMapper modelMapper, MemberService memberService ) {
        this.noticeRepository = noticeRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
//        this.memberServiceClient = memberServiceClient;
        this.memberService = memberService;
    }


    @Override
    @Transactional
    public int insertNotice(NoticeWriteRequestDTO noticeWriteRequestDTO, List<String> roles) throws IllegalAccessException {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", noticeWriteRequestDTO.getMemberId());
//
//        checkRole(flag, map);
        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");

        Notice notice = modelMapper.map(noticeWriteRequestDTO, Notice.class);
        notice.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        notice.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
        notice.setView(0);
        notice.setActive(true);

        try {
            noticeRepository.save(notice);
            return 1;
        }catch(Exception e){
            throw new RuntimeException("공지 입력 실패", e) {
            };
        }
    }

    @Override
    @Transactional
    public int updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO, List<String> roles) throws IllegalAccessException
    {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", noticeUpdateRequestDTO.getMemberId());
//
//        checkRole(flag, map);

        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");


        try {
            Notice notice = noticeRepository.findById(noticeUpdateRequestDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 공지가 존재하지 않습니다."));

            notice.setFixed(!notice.isFixed());
            notice.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

            return 1;
        } catch(NoSuchElementException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("중요도 수정 실패");
        }
    }

    @Override
    @Transactional
    public int deleteNotice(int noticeId, int memberId, List<String> roles) throws IllegalAccessException {
        boolean flag = false;
//        Map<String, String> map = new HashMap<>();
//        map.put("memberId", memberId);
//
//        checkRole(flag, map);

        for(String role : roles){
            if(role.equals("ROLE_ADMIN")){
                flag = true;
                break;
            }
        }

        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");


        try {
            noticeRepository.deleteById(noticeId);
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("공지 삭제 실패") {
            };
        }

    }

//    private void checkRole(boolean flag, Map<String, String> map) throws IllegalAccessException {
//        List<String> RequestRoleList = memberService.checkMemberRole(map);
//
//        for (String role : RequestRoleList) {
//            if(role.equals("ROLE_ADMIN")) {
//                flag = true;
//                break;
//            }
//        }
//        if(!flag)
//            throw new IllegalAccessException("관리자가 아닙니다.");
//    }
}

package com.varchar6.petcast.servicemember.domain.member.command.application.service;

import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.ProfileReqDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.ProfileUpdateRequestDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.MemberUpdateStatusRespDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTests {

    MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
    MemberUpdateRequestDTO memberUpdateRequestDTO = new MemberUpdateRequestDTO();
    ProfileReqDTO profileReqDTO = new ProfileReqDTO();
    ProfileUpdateRequestDTO profileUpdateRequestDTO = new ProfileUpdateRequestDTO();

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @Transactional
    public void 멤버_생성_확인() {
        memberRequestDTO.setLoginId("testId4");
        memberRequestDTO.setPassword("testPw");
        memberRequestDTO.setName("testName");
        memberRequestDTO.setPhone("testPhone");
        memberRequestDTO.setNickname("testNickname");
        memberRequestDTO.setImage("testImage");
        memberRequestDTO.setIntroduction("testIntroduction");
        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);
        assertEquals("testId4", memberResponseDTO.getLoginId());
    }

    @Test
    @Transactional
    public void 회원_비활성화_확인() {
        memberUpdateRequestDTO.setId(9);
        MemberUpdateStatusRespDTO memberUpdateStatusRespDTO = memberService.updateMemberStatus(memberUpdateRequestDTO);
        assertEquals(false, memberUpdateStatusRespDTO.isActive());
    }

    @Test
    @Transactional
    public void 비밀번호_수정_확인() {
        memberUpdateRequestDTO.setId(9);
        memberUpdateRequestDTO.setPassword("1234");
        MemberUpdateStatusRespDTO memberUpdateStatusRespDTO = memberService.updateMemberPwd(memberUpdateRequestDTO);
        assertEquals("1234", memberUpdateStatusRespDTO.getPassword());
    }

    @Test
    @Transactional
    public void 프로필_작성_확인() {
        profileReqDTO.setMemberId(2);
        profileReqDTO.setMemberIntroduction("안녕하세요 또리 아빠입니다.");
        profileReqDTO.setMemberImage("민수 사진1");
        profileReqDTO.setPetName("또리");
        profileReqDTO.setPetIntroduction("4살 또리에요");
        profileReqDTO.setPetGender(MALE);
        profileReqDTO.setPetImage("또리 사진2");
        profileReqDTO.setPetAge(4);
        Boolean isTrue = memberService.registProfile(profileReqDTO);
        assertEquals(true, isTrue);
    }

    @Test
    @Transactional
    public void 고객_정보_수정_확인() {
        profileUpdateRequestDTO.setMemberId(2);
        profileUpdateRequestDTO.setNickname("또리 아빠");
        profileUpdateRequestDTO.setPetId(11);
        profileUpdateRequestDTO.setAge(3);
        Boolean isTrue = memberService.updateMemberProfile(profileUpdateRequestDTO);
        assertEquals(true, isTrue);
    }
}
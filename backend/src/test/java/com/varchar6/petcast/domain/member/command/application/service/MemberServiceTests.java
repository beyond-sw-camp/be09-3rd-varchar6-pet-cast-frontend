package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTests {
    MemberService memberService;

    private static MemberRequestDTO memberRequestDTO = new MemberRequestDTO(
            "testId",
            "testPw",
            "testName",
            "testPhone",
            "testNickname",
            "testImage",
            "testIntroduction"
    );

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    @Transactional
    public void 멤버_생성_확인() {
        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);
        assertNotNull(memberResponseDTO);

    }



}
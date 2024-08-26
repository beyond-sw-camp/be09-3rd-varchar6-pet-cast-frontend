package com.varchar6.petcast.domain.member.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTests {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    public void 멤버아이디로_멤버_조회() {

        int memberId = memberService.selectMemberById(1)
                        .getMemberId();
        Assertions.assertTrue((1 == memberId));
    }

}
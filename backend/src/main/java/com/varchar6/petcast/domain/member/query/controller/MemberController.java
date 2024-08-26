package com.varchar6.petcast.domain.member.query.controller;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "queryMemberController")
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable("memberId") Integer memberId) {
        MemberDTO memberDTO = memberService.selectMemberById(memberId);
        return ResponseEntity.ok()
                .body(memberDTO);
    }

}

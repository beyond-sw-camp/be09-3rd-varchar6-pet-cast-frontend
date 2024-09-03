package com.varchar6.petcast.domain.member.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController(value = "queryMemberController")
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<ResponseMessage> getMember(
            @PathVariable("memberId") Integer memberId
    ) {
        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Login completed")
                                .result(memberService.getMemberInformationById(memberId))
                                .build()
                );
    }

    @GetMapping("/id-check/{loginId}")
    public ResponseEntity<Boolean> checkDoubleByLoginId(@PathVariable("loginId") String loginId){

        return ResponseEntity.ok()
                .body(memberService.checkDoubleByLoginId(loginId));
    }

    @GetMapping("/nickname-check/{nickname}")
    public ResponseEntity<Boolean> checkDoubleByNickName(@PathVariable("nickname") String nickName){

        return ResponseEntity.ok()
                .body(memberService.checkDoubleByNickName(nickName));
    }

    @GetMapping("/search-loginId/{name}/{phone}")
    public ResponseEntity<String> searchLoginIdByNameAndPhone(@PathVariable("name") String name,
                                                              @PathVariable("phone") String phone){

        String memberLoginId = memberService.searchLoginIdByNameAndPhone(name, phone);

        return ResponseEntity.ok().body(memberLoginId);
    }

    @GetMapping("/password-change-possible/{loginId}/{phone}")
    public ResponseEntity<Map<String, Object>> checkIdAndPhone(@PathVariable("loginId") String loginId,
                                                               @PathVariable("phone") String phone) {

        Map<String, Object> result = memberService.checkIdAndPhone(loginId, phone);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/password-check/{id}/{password}")
    public ResponseEntity<ResponseMessage> checkPasswordByIdAndPassword(@PathVariable("id") int id,
                                                                @PathVariable("password") String password){

        String result = memberService.checkPasswordByIdAndPassword(id);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("비밀번호 체크 성공")
                                .result(result)
                                .build()
                );
    }


}

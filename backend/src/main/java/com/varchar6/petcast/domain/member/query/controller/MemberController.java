package com.varchar6.petcast.domain.member.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<ResponseMessage> checkDoubleByLoginId(@PathVariable("loginId") String loginId){

        Boolean answer = memberService.checkDoubleByLoginId(loginId);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("아이디 중복 체크 성공")
                                .result(answer)
                                .build()
                );
    }

    @GetMapping("/nickname-check/{nickname}")
    public ResponseEntity<ResponseMessage> checkDoubleByNickName(@PathVariable("nickname") String nickName){

        Boolean answer = memberService.checkDoubleByNickName(nickName);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("닉네임 중복 체크 성공")
                                .result(answer)
                                .build()
                );
    }

    @GetMapping("/search-loginId/{name}/{phone}")
    public ResponseEntity<ResponseMessage> searchLoginIdByNameAndPhone(@PathVariable("name") String name,
                                                              @PathVariable("phone") String phone){

        String memberLoginId = memberService.searchLoginIdByNameAndPhone(name, phone);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("이름, 전화번호로 로그인 아이디 찾기 성공")
                                .result(memberLoginId)
                                .build()
                );
    }

    @GetMapping("/password-change-possible/{loginId}/{phone}")
    public ResponseEntity<ResponseMessage> checkIdAndPhone(@PathVariable("loginId") String loginId,
                                                               @PathVariable("phone") String phone) {

        int answer = memberService.checkIdAndPhone(loginId, phone);

        Map<String, Object> resultMap = new HashMap<>();

        if ( answer != 0) {
            resultMap.put("id", answer);
            resultMap.put("isPossible", true);
        } else {
            resultMap.put("id", null);
            resultMap.put("isPossible", false);
        }

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("비밀번호 수정 여부 체크 성공")
                                .result(resultMap)
                                .build()
                );
    }

    @GetMapping("/password-check/{id}/{password}")
    public ResponseEntity<ResponseMessage> checkPasswordByIdAndPassword(@PathVariable("id") int id,
                                                                @PathVariable("password") String password){

        String answer = memberService.checkPasswordByIdAndPassword(id);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("비밀번호 체크 성공")
                                .result(password.equals(answer))
                                .build()
                );
    }
}

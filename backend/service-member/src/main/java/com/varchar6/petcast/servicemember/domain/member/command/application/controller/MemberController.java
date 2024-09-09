package com.varchar6.petcast.servicemember.domain.member.command.application.controller;

import com.varchar6.petcast.servicemember.common.response.ResponseMessage;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.request.MemberUpdatePwdReqVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.request.ProfileReqVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.request.RequestRegistUserVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.MemberUpdatePwdRespVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.ProfileRespVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.ResponseRegistUserVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandMemberController")
@RequestMapping("/api/v1/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberController(MemberService memberService, ModelMapper modelMapper) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/test")
    public String getTest(
            @RequestHeader("X-Member-Id") String memberId,
            @RequestHeader("X-Member-Login-Id") String memberLoginId,
            @RequestHeader("X-authorities") String authorities) {
        log.debug("memberId: {}", memberId);
        log.debug("memberLoginId: {}", memberLoginId);
        log.debug("authorities: {}", authorities);

        return "GET working";
    }

    @PostMapping("/test")
    public String postTest() {
        return "POST working";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody RequestRegistUserVO newUser) {

        MemberRequestDTO memberRequestDTO = modelMapper.map(newUser, MemberRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);

        ResponseRegistUserVO responseMember = modelMapper.map(memberResponseDTO, ResponseRegistUserVO.class);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Login completed")
                                .result(responseMember)
                                .build()
                );
    }

    // 회원 상태 비활성화
    @PostMapping("/update-member-status")
    public ResponseEntity<ResponseMessage> updateMemberStatus(@RequestAttribute("X-Member-Id") int memberId){

        MemberUpdateStatusReqDTO memberUpdateRequestDTO = new MemberUpdateStatusReqDTO();
        memberUpdateRequestDTO.setId(memberId);

        MemberUpdateStatusRespDTO memberUpdateStatusRespDTO = memberService.updateStatus(memberUpdateRequestDTO);

        MemberUpdateStatusRespDTO responseMember
                = modelMapper.map(memberUpdateStatusRespDTO, MemberUpdateStatusRespDTO.class);

        return ResponseEntity
            .ok()
            .body(ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message(responseMember.getNickname() + "회원 비활성화 성공")
                .result(responseMember.isActive())
                .build());
    }

    // 회원 비밀번호 수정
    @PutMapping("/update-password")
    public ResponseEntity<ResponseMessage> updateMemberPassword(@RequestAttribute("X-Member-Id") int memberId,
                                                   @RequestBody MemberUpdatePwdReqVO updateMemberPassword) {

        String newPwd = updateMemberPassword.getPassword();

        MemberUpdatePwdReqDTO memberUpdatePwdReqDTO
                = modelMapper.map(updateMemberPassword, MemberUpdatePwdReqDTO.class);
        memberUpdatePwdReqDTO.setId(memberId);

        MemberUpdatePwdRespDTO memberUpdatePwdRespDTO = memberService.updatePwd(memberUpdatePwdReqDTO);

        MemberUpdatePwdRespVO responseMember
                = modelMapper.map(memberUpdatePwdRespDTO,MemberUpdatePwdRespVO.class);


        if(responseMember.getPassword().equals(newPwd)) {
            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message(responseMember.getNickname() + "님 암호 변경 성공")
                            .result(null)
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(responseMember.getNickname() + "님 암호 변경 실패")
                        .result(null)
                        .build());
    }

    // 회원 프로필 등록
    @PostMapping("/regist-member-profile")
    public ResponseEntity<ResponseMessage> registProfile(@RequestHeader("X-Member-Id") int memberId,
                                                          @RequestBody ProfileReqVO newProfile) {

        ProfileReqDTO profileReqDTO = modelMapper.map(newProfile,ProfileReqDTO.class);
        profileReqDTO.setMemberId(memberId);

        ProfileRespDTO profileRespDTO = memberService.registProfile(profileReqDTO);

        ProfileRespVO profileRespVO = modelMapper.map(profileRespDTO,ProfileRespVO.class);

        if(profileRespDTO.getResult() == 1) {
            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message(profileRespVO.getNickname() + "님 프로필 생성 성공")
                            .result(null)
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(profileRespVO.getNickname() + "님 프로필 생성 실패")
                        .result(null)
                        .build());
    }

    // 회원 프로필 업데이트
    @PutMapping("/update-member-profile")
    public ResponseEntity<ResponseMessage> updateProfile(@RequestAttribute("X-Member-Id") int memberId,
                                                               @RequestBody ProfileReqVO updateProfile){

        ProfileUpdateReqDTO profileUpdateReqDTO = modelMapper.map(updateProfile,ProfileUpdateReqDTO.class);
        profileUpdateReqDTO.setId(memberId);

        ProfileUpdateRespDTO profileUpdateRespDTO = memberService.updateProfile(profileUpdateReqDTO);


        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("고객 프로필 수정 성공")
                        .result(answer)
                        .build());
    }
}

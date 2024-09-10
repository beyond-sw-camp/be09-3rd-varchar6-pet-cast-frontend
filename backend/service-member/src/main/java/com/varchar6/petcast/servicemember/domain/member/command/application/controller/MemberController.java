package com.varchar6.petcast.servicemember.domain.member.command.application.controller;

import com.varchar6.petcast.servicemember.common.response.ResponseMessage;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.MemberUpdateStatusRespVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.ProfileRegistRespVO;
import com.varchar6.petcast.servicemember.domain.member.command.application.vo.response.ProfileUpdateRespVO;
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
    public ResponseEntity<ResponseMessage> updateMemberStatus(@RequestHeader("X-Member-Id") int memberId){

        MemberReqDTO memberReqDTO = new MemberReqDTO();
        memberReqDTO.setId(memberId);

        MemberRespDTO memberRespDTO = memberService.updateStatus(memberReqDTO);

        if(memberRespDTO.getResult() == 1) {

            MemberUpdateStatusRespVO responseMember =  modelMapper.map(memberRespDTO,MemberUpdateStatusRespVO.class);

            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message(responseMember.getNickname() + "회원 비활성화 성공")
                            .result(responseMember.isActive())
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .message("회원 비활성화 실패")
                        .result(null)
                        .build());
    }


    // 회원 비밀번호 수정
    @PutMapping("/update-password")
    public ResponseEntity<ResponseMessage> updateMemberPassword(@RequestHeader("X-Member-Id") int memberId,
                                                   @RequestBody MemberUpdatePwdReqVO updateMemberPassword) {

        String newPwd = updateMemberPassword.getPassword();

        MemberReqDTO memberReqDTO = modelMapper.map(updateMemberPassword,MemberReqDTO.class);
        memberReqDTO.setId(memberId);

        MemberRespDTO memberRespDTO = memberService.updatePassword(memberReqDTO);

        if(memberRespDTO.getResult() == 1) {

            MemberUpdateStatusRespVO responseMember
                    = modelMapper.map(memberRespDTO,MemberUpdateStatusRespVO.class);

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
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .message("암호 변경 실패")
                        .result(null)
                        .build());
    }

    // 회원 프로필 등록
    @PostMapping("/regist-member-profile")
    public ResponseEntity<ResponseMessage> registProfile(@RequestHeader("X-Member-Id") int memberId,
                                                          @RequestBody ProfileRegistReqVO newProfile) {

        ProfileReqDTO profileReqDTO = modelMapper.map(newProfile,ProfileReqDTO.class);
        profileReqDTO.setMemberId(memberId);

        ProfileRespDTO profileRespDTO = memberService.registProfile(profileReqDTO);

        if(profileRespDTO.getResult() == 1) {

            ProfileRegistRespVO profileRegistRespVO = modelMapper.map(profileRespDTO, ProfileRegistRespVO.class);

            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message(profileRegistRespVO.getNickname() + "님 프로필 생성 성공")
                            .result(null)
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .message("프로필 생성 실패")
                        .result(null)
                        .build());
    }

    // 회원 프로필 업데이트
    @PutMapping("/update-member-profile")
    public ResponseEntity<ResponseMessage> updateProfile(@RequestAttribute("X-Member-Id") int memberId,
                                                         @RequestBody ProfileUpdateReqVO updateProfile) {

        ProfileReqDTO profileReqDTO = modelMapper.map(updateProfile, ProfileReqDTO.class);
        profileReqDTO.setMemberId(memberId);

        ProfileRespDTO profileRespDTO = memberService.updateProfile(profileReqDTO);

        if (profileRespDTO.getResult() == 1) {

            ProfileUpdateRespVO profileUpdateRespVO = modelMapper.map(profileRespDTO, ProfileUpdateRespVO.class);

            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message(profileUpdateRespVO.getNickname() + "님 프로필 수정 성공")
                            .result(null)
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .message("프로필 수정 실패")
                        .result(null)
                        .build());
    }

    // 회원 프로필 반려견 정보 추가
    @PostMapping("/regist-pet")
    public ResponseEntity<ResponseMessage> registPet(@RequestAttribute("X-Member-Id") int memberId,
                                                     @RequestBody PetRegistReqVO newPet) {

        PetReqDTO petReqDTO = modelMapper.map(newPet, PetReqDTO.class);
        petReqDTO.setMemberId(memberId);

        PetRespDTO petRespDTO = memberService.registPet(petReqDTO);

        if (petRespDTO.getResult() == 1) {
            return ResponseEntity
                    .ok()
                    .body(ResponseMessage.builder()
                            .httpStatus(HttpStatus.OK.value())
                            .message("반려견 정보 추가 성공")
                            .result(null)
                            .build());
        }

        return ResponseEntity
                .ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .message("반려견 정보 추가 실패")
                        .result(null)
                        .build());
    }
}

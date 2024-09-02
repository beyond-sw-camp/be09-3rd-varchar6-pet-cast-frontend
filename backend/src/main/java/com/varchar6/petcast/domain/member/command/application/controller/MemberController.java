package com.varchar6.petcast.domain.member.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.domain.member.command.application.vo.request.RequestRegistUserVO;
import com.varchar6.petcast.domain.member.command.application.vo.response.ResponseRegistUserVO;
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
    public String getTest() {
        return "get working";
    }

    @PostMapping("/test")
    public String postTest() {
        return "post working";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody RequestRegistUserVO newUser){

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
}

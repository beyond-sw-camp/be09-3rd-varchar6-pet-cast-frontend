package com.varchar6.petcast.domain.member.command.application.controller;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.domain.member.command.application.vo.RequestRegistUserVO;
import com.varchar6.petcast.domain.member.command.application.vo.ResponseRegistUserVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandMemberController")
@RequestMapping("/api/v1/member")
@Slf4j
public class MemberController {

    /* 설명. 의존성 주입 */
    private final MemberService memberService;
    private ModelMapper modelMapper;
    private Environment env;

    /* 설명. 매개변수 생성자 */
    @Autowired
    public MemberController(MemberService memberService, ModelMapper modelMapper, Environment env) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
        this.env = env;
    }

    @GetMapping("/health")
    public String status(){
        return "I'm Working in User Service " + env.getProperty("local.server.port");
    }

    /* 설명. 회원 가입 */
    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistUserVO> registMember(@RequestBody RequestRegistUserVO newUser){

        MemberRequestDTO memberRequestDTO = modelMapper.map(newUser, MemberRequestDTO.class);

        MemberResponseDTO memberResponseDTO = memberService.registMember(memberRequestDTO);

        ResponseRegistUserVO reponseMember = modelMapper.map(memberResponseDTO, ResponseRegistUserVO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(reponseMember);
    }
}

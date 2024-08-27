package com.varchar6.petcast.domain.member.command.application.controller;

import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController(value = "commandMemberController")
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> register(@RequestBody MemberRequestDTO memberRequestDTO) {
        URI uri = linkTo(methodOn(MemberController.class)
                .register(memberRequestDTO))
                .slash("{id}").toUri();
        return ResponseEntity.created(uri)
                        .body(memberService.registerMember(memberRequestDTO));

    }

}

package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.dto.mapper.MemberMapper;
import com.varchar6.petcast.domain.member.command.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value="commandMemberService")
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {
        return memberMapper.entityToResponseDTO(
                memberRepository.save(
                        memberMapper.requestDTOToEntity(memberRequestDTO)
                )
        );
    }
}

package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.mapper.MemberMapperTemplate;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value="commandMemberService")
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapperTemplate memberMapperTemplate;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapperTemplate memberMapperTemplate) {
        this.memberRepository = memberRepository;
        this.memberMapperTemplate = memberMapperTemplate;
    }

    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {
        return memberMapperTemplate.entityToResponseDTO(
                memberRepository.save(
                        memberMapperTemplate.requestDTOToEntity(memberRequestDTO)
                )
        );
    }
}

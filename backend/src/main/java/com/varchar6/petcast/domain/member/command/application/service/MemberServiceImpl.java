package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {

//        memberRequestDTO.setUserId(UUID.randomUUID().toString());

        Member newMember = requestDTOToEntity(memberRequestDTO);

        /* 설명. UserId 잘 들어갔는지 확인*/
        log.info("memberRequestDTO 구조 확인: {}",newMember);

        /* 설명. pwd 암호화 진행*/

        return entityToResponseDTO(memberRepository.save(newMember));
    }


    public static Member requestDTOToEntity(MemberRequestDTO memberRequestDTO) {
        return Member.builder()
                .loginId(memberRequestDTO.getLoginId())
                .password(memberRequestDTO.getPassword())
                .name(memberRequestDTO.getName())
                .phone(memberRequestDTO.getPhone())
                .nickname(memberRequestDTO.getNickname())
                .image(memberRequestDTO.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .activeYn(true)
                .introduction(memberRequestDTO.getIntroduction())
                .build();
    }

    public static MemberResponseDTO entityToResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getNickname())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .activeYn(member.isActiveYn())
                .introduction(member.getIntroduction())
                .build();
    }
}

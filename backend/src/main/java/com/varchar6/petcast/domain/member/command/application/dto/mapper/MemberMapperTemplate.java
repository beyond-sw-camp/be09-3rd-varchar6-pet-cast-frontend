package com.varchar6.petcast.domain.member.command.application.dto.mapper;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MemberMapperTemplate {
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);

    public Member requestDTOToEntity(MemberRequestDTO memberRequestDTO) {
        return Member.builder()
                .loginId(memberRequestDTO.getLoginId())
                .password(memberRequestDTO.getPassword())
                .name(memberRequestDTO.getName())
                .phone(memberRequestDTO.getPhone())
                .nickname(memberRequestDTO.getNickname())
                .image(memberRequestDTO.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(formatter)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(formatter)
                )
                .activeYn(true)
                .introduction(memberRequestDTO.getIntroduction())
                .build();
    }

    public MemberResponseDTO entityToResponseDTO(Member member) {
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

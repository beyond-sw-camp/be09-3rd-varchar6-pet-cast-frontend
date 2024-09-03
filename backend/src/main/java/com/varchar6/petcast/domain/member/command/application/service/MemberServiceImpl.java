package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberDeleteRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMember;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleType;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             RoleRepository roleRepository,
                             ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {

        // 비밀번호 암호화
        memberRequestDTO.setPassword(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()));

        // Member 생성
        Member member = memberRepository.save(requestDTOToEntity(memberRequestDTO));

        // 회원 권한 부여
        roleRepository.save(RoleMember.builder()
                .memberId(member.getId())
                .roleId(RoleType.CUSTOMER.getRoleId())
                .build());

        return entityToResponseDTO(member);
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMemberStatus(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();

        Member updateMember = Member.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(false)
                .introduction(member.getIntroduction())
                .build();

        log.info("DTO 값 확인: {}", updateMember);

        memberRepository.save(updateMember);

        MemberResponseDTO responseDTO = modelMapper.map(updateMember,MemberResponseDTO.class);

        log.info("DTO 값 확인: {}", responseDTO);

        return responseDTO;
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMemberPwd(MemberUpdateRequestDTO memberUpdateRequestDTO) {

        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();
        member.setPassword(memberUpdateRequestDTO.getPassword());
        member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);

        return responseDTO;
    }

    @Override
    public MemberResponseDTO deleteMember(MemberDeleteRequestDTO memberDeleteRequestDTO) {
        Member member = memberRepository.findById(memberDeleteRequestDTO.getId()).orElseThrow();
        member.setActive(memberDeleteRequestDTO.getActive());
        member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);
        return responseDTO;
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
                .active(true)
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
                .active(member.isActive())
                .introduction(member.getIntroduction())
                .build();
    }
}

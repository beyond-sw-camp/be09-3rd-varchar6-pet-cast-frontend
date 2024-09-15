package com.varchar6.petcast.servicemember.domain.member.command.application.service;

import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.*;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.*;
import com.varchar6.petcast.servicemember.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.servicemember.domain.member.command.domain.repository.PetRepository;
import com.varchar6.petcast.servicemember.domain.member.command.domain.repository.RoleRepository;
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
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             RoleRepository roleRepository,
                             PetRepository petRepository,
                             ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.petRepository = petRepository;
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
    public MemberRespDTO updateStatus(MemberReqDTO memberReqDTO) {

        Member member = memberRepository.findById(memberReqDTO.getId()).orElseThrow();
        MemberRespDTO memberRespDTO = new MemberRespDTO();

        if (member != null) {
            Member updateMember = Member.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .password(member.getPassword())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .nickname(member.getNickname())
                    .image(member.getImage())
                    .createdAt(member.getCreatedAt())
                    .updatedAt(
                            LocalDateTime.now()
                                    .format(FORMATTER)
                    )
                    .active(false)
                    .introduction(member.getIntroduction())
                    .build();

            memberRepository.save(updateMember);

            memberRespDTO.setResult(1);
            memberRespDTO.setActive(false);
            memberRespDTO.setNickname(member.getNickname());
        } else {
            memberRespDTO.setResult(0);
        }

        return memberRespDTO;
    }

    @Override
    @Transactional
    public MemberRespDTO updatePassword(MemberReqDTO memberReqDTO) {

        Member member = memberRepository.findById(memberReqDTO.getId()).orElseThrow();
        MemberRespDTO memberRespDTO = new MemberRespDTO();

        if(member != null) {
            Member updateMember = Member.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(memberReqDTO.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .introduction(member.getIntroduction())
                .build();

            memberRepository.save(updateMember);

            memberRespDTO.setResult(1);
            memberRespDTO.setPassword(memberReqDTO.getPassword());
            memberRespDTO.setNickname(member.getNickname());
        } else {
            memberRespDTO.setResult(0);
        }
        return memberRespDTO;
    }



    @Override
    @Transactional
    public ProfileRespDTO registProfile(ProfileReqDTO profileReqDTO) {

        ProfileRespDTO profileRespDTO = new ProfileRespDTO();

        Member member = memberRepository.findById(profileReqDTO.getMemberId()).orElseThrow();

        if (member != null) {
            member.setImage(profileReqDTO.getMemberImage());
            member.setIntroduction(profileReqDTO.getMemberIntroduction());
            member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
            memberRepository.save(member);

            log.info("배열 값: {} ", profileReqDTO.getPetInfo().toArray());

            for (int i = 0; i < profileReqDTO.getPetInfo().size(); i++) {
                 Pet petInfo = profileReqDTO.getPetInfo().get(i);

                 Pet pet = Pet.builder()
                         .id(petInfo.getId())
                         .name(petInfo.getName())
                         .introduction(petInfo.getIntroduction())
                         .gender(petInfo.getGender())
                         .image(petInfo.getImage())
                         .age(petInfo.getAge())
                         .createdAt(LocalDateTime.now().format(FORMATTER))
                         .updatedAt(LocalDateTime.now().format(FORMATTER))
                         .active(true)
                         .memberId(member.getId())
                         .build();

                 petRepository.save(pet);
            }

            profileRespDTO.setMemberNickname(member.getNickname());
            profileRespDTO.setResult(1);
        } else{
            profileRespDTO.setResult(0);
        }

        return profileRespDTO;
    }

    @Override
    @Transactional
    public ProfileRespDTO updateProfile(ProfileReqDTO profileReqDTO) {

        Member member = memberRepository.findById(profileReqDTO.getMemberId()).orElseThrow();
        ProfileRespDTO profileRespDTO = new ProfileRespDTO();

        if (member != null){
            member.setIntroduction(profileReqDTO.getMemberIntroduction());
            member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
            memberRepository.save(member);

            for (int i = 0; i < profileReqDTO.getPetInfo().size(); i++) {
                Pet updatePet = profileReqDTO.getPetInfo().get(i);

                Pet pet = petRepository.findByMemberIdAndName(member.getId(),updatePet.getName());

                pet.setAge(updatePet.getAge());
                pet.setGender(updatePet.getGender());
                pet.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

                petRepository.save(pet);
            }

            profileRespDTO.setMemberNickname(member.getNickname());
            profileRespDTO.setResult(1);

            return profileRespDTO;
        } else {
            profileRespDTO.setResult(0);

            return profileRespDTO;
        }
    }

    @Override
    public PetRespDTO registPet(PetReqDTO petReqDTO) {

        Pet pet = Pet.builder()
                .id(petReqDTO.getId())
                .name(petReqDTO.getName())
                .introduction(petReqDTO.getIntroduction())
                .gender(petReqDTO.getGender())
                .image(petReqDTO.getImage())
                .age(petReqDTO.getAge())
                .createdAt(LocalDateTime.now().format(FORMATTER))
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .memberId(petReqDTO.getMemberId())
                .build();

        petRepository.save(pet);

        PetRespDTO petRespDTO = new PetRespDTO();
        petRespDTO.setResult(1);

        return petRespDTO;
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

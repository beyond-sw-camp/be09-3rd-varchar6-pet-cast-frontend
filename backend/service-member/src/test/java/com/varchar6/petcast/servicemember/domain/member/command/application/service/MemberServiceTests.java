package com.varchar6.petcast.servicemember.domain.member.command.application.service;

import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.MemberReqDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.PetReqDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.ProfileReqDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.MemberRespDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.PetRespDTO;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.ProfileRespDTO;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Pet;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTests {

    MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
    MemberReqDTO memberReqDTO = new MemberReqDTO();
    ProfileReqDTO profileReqDTO = new ProfileReqDTO();
    PetReqDTO petReqDTO = new PetReqDTO();

    MemberRespDTO memberRespDTO = new MemberRespDTO();
    ProfileRespDTO profileRespDTO = new ProfileRespDTO();
    PetRespDTO petRespDTO = new PetRespDTO();

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @Transactional
    public void 멤버_생성_확인() {
        memberRequestDTO.setLoginId("testId4");
        memberRequestDTO.setPassword("testPw");
        memberRequestDTO.setName("testName");
        memberRequestDTO.setPhone("testPhone");
        memberRequestDTO.setNickname("testNickname");
        memberRequestDTO.setImage("testImage");
        memberRequestDTO.setIntroduction("testIntroduction");
        MemberResponseDTO memberResponseDTO = memberService.registerMember(memberRequestDTO);
        assertEquals("testId4", memberResponseDTO.getLoginId());
    }

    @Test
    @Transactional
    public void 회원_비활성화_확인() {
        memberReqDTO.setId(9);
        memberRespDTO  = memberService.updateStatus(memberReqDTO);

        assertEquals(false, memberRespDTO.isActive());
    }

    @Test
    @Transactional
    public void 비밀번호_수정_확인() {
        memberReqDTO.setId(9);
        memberReqDTO.setPassword("1234");
        memberRespDTO  = memberService.updatePassword(memberReqDTO);
        assertEquals("1234", memberRespDTO.getPassword());
    }

    @Test
    @Transactional
    public void 프로필_작성_확인() {
        profileReqDTO.setMemberId(2);
        profileReqDTO.setMemberIntroduction("안녕하세요 또리 아빠입니다.");
        profileReqDTO.setMemberImage("민수 사진1");
        List<Pet> petInfo = new ArrayList<>();

        Pet pet1 = Pet.builder()
                .name("또리")
                .age(2)
                .introduction("귀여운 강아지")
                .gender(Gender.MALE)
                .image("pet1.jpg")
                .memberId(2)
                .build();

        Pet pet2 = Pet.builder()
                .name("나비")
                .age(3)
                .introduction("호기심 많은 고양이")
                .gender(Gender.FEMALE)
                .image("pet2.jpg")
                .memberId(2)
                .build();

        petInfo.add(pet1);
        petInfo.add(pet2);

        profileReqDTO.setPetInfo(petInfo);

        profileRespDTO = memberService.registProfile(profileReqDTO);

        assertEquals(1, profileRespDTO.getResult());
    }

    @Test
    @Transactional
    public void 프로필_수정_확인() {
        profileReqDTO.setMemberId(24);
        profileReqDTO.setMemberIntroduction("안녕하세요 3살 된 루리 아빠입니다.");
        List<Pet> petInfo = new ArrayList<>();

        Pet pet1 = Pet.builder()
                .name("감자")
                .age(3)
                .gender(Gender.FEMALE)
                .build();

        petInfo.add(pet1);
        profileReqDTO.setPetInfo(petInfo);

        profileRespDTO = memberService.updateProfile(profileReqDTO);

        assertEquals(1, profileRespDTO.getResult());
    }

    @Test
    @Transactional
    public void 새로운_반려동물_등록() {

        petReqDTO.setName("치치");
        petReqDTO.setIntroduction("장난끼 많은 강아지");
        petReqDTO.setGender(Gender.OTHERS);
        petReqDTO.setImage("치치 사진1.jpg");
        petReqDTO.setAge(1);
        petReqDTO.setMemberId(2);
        petRespDTO = memberService.registPet(petReqDTO);

        assertEquals(1, petRespDTO.getResult());
    }
}
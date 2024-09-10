package com.varchar6.petcast.servicemember.domain.member.command.application.service;

import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.*;

public interface MemberService {

    MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO);

    MemberRespDTO updateStatus(MemberReqDTO memberReqDTO);

    MemberRespDTO updatePassword(MemberReqDTO memberReqDTO);

    ProfileRespDTO registProfile(ProfileReqDTO profileReqDTO);

    ProfileRespDTO updateProfile(ProfileReqDTO profileReqDTO);

    PetRespDTO registPet(PetReqDTO petReqDTO);
}

package com.varchar6.petcast.servicemember.domain.member.command.application.service;

import com.varchar6.petcast.servicemember.domain.member.command.application.dto.request.*;
import com.varchar6.petcast.servicemember.domain.member.command.application.dto.response.*;

public interface MemberService {

    MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO);

    MemberUpdateStatusRespDTO updateStatus(MemberUpdateStatusReqDTO memberUpdateRequestDTO);

    MemberUpdatePwdRespDTO updatePwd(MemberUpdatePwdReqDTO memberUpdatePwdReqDTO);

    ProfileRespDTO registProfile(ProfileReqDTO profileReqDTO);

    ProfileUpdateRespDTO updateProfile(ProfileUpdateReqDTO profileUpdateReqDTO);
}

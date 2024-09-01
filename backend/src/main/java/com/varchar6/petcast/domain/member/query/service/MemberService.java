package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    MemberDTO getMemberInformationById(int memberId);
}

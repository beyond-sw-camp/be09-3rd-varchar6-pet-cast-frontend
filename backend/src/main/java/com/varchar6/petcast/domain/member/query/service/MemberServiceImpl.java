package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service(value="queryMemberService")
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDTO getMemberInformationById(int memberId) {
        return memberMapper.selectMemberByMemberId(memberId);
    }

    @Override
    public Boolean checkDoubleByLoginId(String loginId) {

        MemberDTO memberDTO = memberMapper.checkDoubleByLoginId(loginId);

        if (memberDTO == null || memberDTO.getLoginId() == null) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkDoubleByNickName(String nickName) {

        MemberDTO memberDTO = memberMapper.checkDoubleByNickName(nickName);

        if(memberDTO == null || memberDTO.getNickname() == null) {return true;}

        return false;
    }

    @Override
    public String searchLoginIdByNameAndPhone(String name, String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("phone", phone);
        MemberDTO memberDTO = memberMapper.searchLoginIdByNameAndPhone(params);

        return memberDTO.getLoginId();
    }

    @Override
    public Map<String, Object> checkIdAndPhone(String loginId, String phone) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("loginId", loginId);
        resultMap.put("phone", phone);

        MemberDTO memberDTO = memberMapper.checkIdAndPhone(resultMap);

        Map<String,Object> response = new HashMap<>();

        if(memberDTO != null) {
            response.put("canResetPassword", true);
        } else{
            response.put("canResetPassword", false);
        }

        return response;
    }

    @Override
    public String checkPasswordByIdAndPassword(int id) {

        MemberDTO memberDTO = memberMapper.checkPasswordByIdAndPassword(id);

        if(memberDTO != null) {
            return memberDTO.getPassword();
        } else{
            return null;
        }
    }
}

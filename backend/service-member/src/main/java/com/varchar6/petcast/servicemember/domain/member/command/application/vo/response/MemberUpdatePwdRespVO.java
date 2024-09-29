package com.varchar6.petcast.servicemember.domain.member.command.application.vo.response;

import lombok.Data;

import java.util.Objects;

@Data
public class MemberUpdatePwdRespVO {

    private String nickname;
    private String password;
}

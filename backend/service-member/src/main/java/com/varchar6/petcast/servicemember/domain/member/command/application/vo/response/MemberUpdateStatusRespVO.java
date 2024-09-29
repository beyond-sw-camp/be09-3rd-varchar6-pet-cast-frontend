package com.varchar6.petcast.servicemember.domain.member.command.application.vo.response;

import lombok.Data;

@Data
public class MemberUpdateStatusRespVO {

    private String nickname;
    private boolean active;
}

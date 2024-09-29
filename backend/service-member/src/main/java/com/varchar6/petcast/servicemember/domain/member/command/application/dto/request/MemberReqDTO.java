package com.varchar6.petcast.servicemember.domain.member.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqDTO {

    private int id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String introduction;
}

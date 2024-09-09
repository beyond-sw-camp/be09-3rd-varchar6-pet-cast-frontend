package com.varchar6.petcast.servicemember.domain.member.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdatePwdRespDTO {

    private int id;
    private String password;
}

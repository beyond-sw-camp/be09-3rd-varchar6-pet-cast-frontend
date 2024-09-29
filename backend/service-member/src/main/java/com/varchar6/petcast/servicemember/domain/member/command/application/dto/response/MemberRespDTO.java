package com.varchar6.petcast.servicemember.domain.member.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRespDTO {

    private int id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private String introduction;

    // 실행 결과
    private int result;
}

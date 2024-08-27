package com.varchar6.petcast.domain.member.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String introduction;
}

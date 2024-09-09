package com.varchar6.petcast.servicemember.domain.member.command.application.dto.response;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateStatusRespDTO {

    private String nickname;
    private boolean active;

}

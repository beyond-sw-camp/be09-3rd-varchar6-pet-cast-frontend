package com.varchar6.petcast.servicemember.domain.member.command.application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRespDTO {

    private int result;
    private String nickname;

    private String petName;
}

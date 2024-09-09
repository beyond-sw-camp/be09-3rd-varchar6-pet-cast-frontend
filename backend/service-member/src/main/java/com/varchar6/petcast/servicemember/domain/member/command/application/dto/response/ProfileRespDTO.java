package com.varchar6.petcast.servicemember.domain.member.command.application.dto.response;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRespDTO {

    private int result;
    private String nickname;

}

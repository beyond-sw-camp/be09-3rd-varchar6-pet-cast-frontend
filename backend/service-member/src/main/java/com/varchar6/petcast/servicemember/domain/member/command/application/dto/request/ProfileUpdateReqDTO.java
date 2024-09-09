package com.varchar6.petcast.servicemember.domain.member.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateReqDTO {

    private int id;
    private String introduction;

    private String petName;
    private int age;
    private String gender;

}

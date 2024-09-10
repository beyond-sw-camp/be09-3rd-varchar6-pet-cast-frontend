package com.varchar6.petcast.servicemember.domain.member.command.application.dto.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetReqDTO {

    private int id;
    private String name;
    private String introduction;
    private Gender gender;
    private String image;
    private int age;
    private int memberId;

}

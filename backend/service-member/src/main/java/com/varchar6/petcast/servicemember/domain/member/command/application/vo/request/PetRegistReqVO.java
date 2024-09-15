package com.varchar6.petcast.servicemember.domain.member.command.application.vo.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import lombok.Data;

@Data
public class PetRegistReqVO {

    private String name;
    private String introduction;
    private Gender gender;
    private String image;
    private int age;
}

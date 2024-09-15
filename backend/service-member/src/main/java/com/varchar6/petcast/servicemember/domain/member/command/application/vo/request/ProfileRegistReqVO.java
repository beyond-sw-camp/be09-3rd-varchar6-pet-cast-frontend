package com.varchar6.petcast.servicemember.domain.member.command.application.vo.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Pet;
import lombok.Data;

import java.util.List;

@Data
public class ProfileRegistReqVO {

    private String memberIntroduction;
    private String memberImage;

    private List<Pet> petInfo;
}

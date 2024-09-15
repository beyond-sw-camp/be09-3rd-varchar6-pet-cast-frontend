package com.varchar6.petcast.servicemember.domain.member.command.application.vo.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Pet;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class ProfileUpdateReqVO {

    private String memberIntroduction;

    private List<Pet> petInfo;
}

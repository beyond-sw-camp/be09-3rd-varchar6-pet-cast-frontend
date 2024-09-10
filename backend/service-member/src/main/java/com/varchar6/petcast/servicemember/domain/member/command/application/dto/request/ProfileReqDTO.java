package com.varchar6.petcast.servicemember.domain.member.command.application.dto.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Pet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileReqDTO {

    private int memberId;
    private String memberIntroduction;
    private String memberImage;

    private List<Pet> petInfo;
}

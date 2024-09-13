package com.varchar6.petcast.servicemember.domain.member.command.application.dto.request;

import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Gender;
import com.varchar6.petcast.servicemember.domain.member.command.domain.aggregate.Pet;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileReqDTO {

    private int memberId;
    private String memberIntroduction;
    private String memberImage;

    private List<Pet> petInfo;
}

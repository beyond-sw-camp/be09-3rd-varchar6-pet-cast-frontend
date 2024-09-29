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
public class PetRespDTO {

    private int id;
    private String name;
    private String introduction;
    private Gender gender;
    private String image;
    private int age;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int memberId;

    private int result;
}

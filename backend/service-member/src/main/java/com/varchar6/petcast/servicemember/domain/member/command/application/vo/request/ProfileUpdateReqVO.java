package com.varchar6.petcast.servicemember.domain.member.command.application.vo.request;

import lombok.Data;

import java.util.Objects;

@Data
public class ProfileUpdateReqVO {

    private String introduction;

    private String petName;
    private int age;
    private String gender;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileUpdateReqVO that = (ProfileUpdateReqVO) o;
        return age == that.age && Objects.equals(introduction, that.introduction) && Objects.equals(petName, that.petName) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(introduction, petName, age, gender);
    }
}

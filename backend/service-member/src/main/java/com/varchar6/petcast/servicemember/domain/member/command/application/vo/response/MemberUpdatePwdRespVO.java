package com.varchar6.petcast.servicemember.domain.member.command.application.vo.response;

import lombok.Data;

import java.util.Objects;

@Data
public class MemberUpdatePwdRespVO {

    private String nickname;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberUpdatePwdRespVO that = (MemberUpdatePwdRespVO) o;
        return Objects.equals(nickname, that.nickname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, password);
    }
}

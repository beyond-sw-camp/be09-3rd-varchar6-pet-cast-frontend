package com.varchar6.petcast.domain.member.command.application.vo;

import lombok.Data;

/* 설명. postman을 통해서 받아올 값들을 저장해주는 Command 객체 */
@Data
public class RequestRegistUserVO {
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String introduction;
}

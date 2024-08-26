package com.varchar6.petcast.domain.member.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int memberId;
    private String nickname;
    private String createdAt;
}

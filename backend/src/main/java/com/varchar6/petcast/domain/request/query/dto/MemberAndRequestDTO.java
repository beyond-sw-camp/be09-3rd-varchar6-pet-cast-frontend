package com.varchar6.petcast.domain.request.query.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@ToString
@Builder
public class MemberAndRequestDTO {
    private String nickname;
    private List<RequestDTO> requests;
}

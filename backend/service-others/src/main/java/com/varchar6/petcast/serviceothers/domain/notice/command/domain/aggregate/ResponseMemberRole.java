package com.varchar6.petcast.serviceothers.domain.notice.command.domain.aggregate;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ResponseMemberRole {
    private String name;
    private Boolean active;
}

package com.varchar6.petcast.domain.gather.command.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestSendDTO {
    private int userId;
    private int gatherId;
    private String invitationId;
    private String invitationContent;
}

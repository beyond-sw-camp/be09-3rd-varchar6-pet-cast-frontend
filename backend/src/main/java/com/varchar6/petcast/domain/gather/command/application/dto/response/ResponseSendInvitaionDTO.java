package com.varchar6.petcast.domain.gather.command.application.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseSendDTO {
    private int userId;
    private int gatherId;
    private String invitationId;
    private String invitationContent;

    private String message;
}

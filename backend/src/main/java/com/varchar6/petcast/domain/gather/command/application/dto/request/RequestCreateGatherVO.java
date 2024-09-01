package com.varchar6.petcast.domain.gather.command.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class RequestCreateGatherVO {
    private String name;
    private String content;
    private int number;
    private String url;
    private String updatedAt;
    private String createdAt;
    private boolean active;
    private String invitationId;
    private String invitationContent;


    private int userId;
}

package com.varchar6.petcast.domain.gather.command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateGatherDTO {
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

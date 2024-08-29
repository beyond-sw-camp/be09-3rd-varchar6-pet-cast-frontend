package com.varchar6.petcast.domain.gather.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GatherDTO {
    private int id;
    private String name;
    private String content;
    private int count;
    private String url;
    private java.util.Date updatedAt;
    private java.util.Date createdAt;
    private boolean activeYn;
    private String invitationId;
    private String intitationContent;
}

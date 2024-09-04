package com.varchar6.petcast.domain.event.command.application.dto.response;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatus;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private int id;
    private String title;
    private String content;
    private String image;
    private EventStatus status;
    private Integer companyId;
    private Integer memberId;

}

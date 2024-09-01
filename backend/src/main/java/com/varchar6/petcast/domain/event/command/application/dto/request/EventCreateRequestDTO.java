package com.varchar6.petcast.domain.event.command.application.dto.request;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequestDTO {
    private String title;
    private String content;
    private EventStatus status;
    private String image;
    private Integer companyId;
    private Integer memberId;

}

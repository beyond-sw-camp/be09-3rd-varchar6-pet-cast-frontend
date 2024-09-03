package com.varchar6.petcast.domain.request.command.application.dto;

import com.varchar6.petcast.domain.request.command.domain.aggregate.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestsRequestDTO {
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private Status status;
    private String updatedAt;
    private String createdAt;
    private boolean active;
    private int companyId;
    private int memberId;
}

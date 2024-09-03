package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstimatesRequestDTO {
    private int id;
    private int expectedCost;
    private EstimatesStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int proposalId;
    private int companyId;
}

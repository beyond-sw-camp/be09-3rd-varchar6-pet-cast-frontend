package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class EstimateResponseDTO {
    private int id;
    private int expectedCost;
    private String createdAt;
    private String updatedAt;
    private EstimatesStatus status;
    private boolean active;


}

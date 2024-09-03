package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EstimatesResponseDTO {
    private int expectedCost;
}

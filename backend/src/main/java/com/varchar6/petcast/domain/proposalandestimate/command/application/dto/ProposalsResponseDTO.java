package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalsResponseDTO {
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;
}

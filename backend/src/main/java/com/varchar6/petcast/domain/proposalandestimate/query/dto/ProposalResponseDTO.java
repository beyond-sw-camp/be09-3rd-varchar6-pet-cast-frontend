package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.ProposalsStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalResponseDTO {
    private int id;
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private ProposalsStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
}

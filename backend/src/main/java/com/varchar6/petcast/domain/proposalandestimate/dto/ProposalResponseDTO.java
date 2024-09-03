package com.varchar6.petcast.domain.proposalandestimate.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.ProposalsStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalResponseDTO {
    private int id;
    private String content;
    private String location;
    private String time;
    private int cost;
    private ProposalsStatus status;
    private String created_at;
    private String updated_at;
    private boolean active;
}

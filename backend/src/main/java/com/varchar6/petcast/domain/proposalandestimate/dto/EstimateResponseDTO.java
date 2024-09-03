package com.varchar6.petcast.domain.proposalandestimate.dto;


import com.varchar6.petcast.domain.proposalandestimate.aggregate.EstimateStatus;
import com.varchar6.petcast.domain.proposalandestimate.aggregate.Proposal;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class EstimateResponseDTO {
    private int id;
    private int cost;
    private EstimateStatus status;
    private String created_at;
    private String updated_at;
    private boolean active;
    private int proposal;
    private int companyInfo;
}

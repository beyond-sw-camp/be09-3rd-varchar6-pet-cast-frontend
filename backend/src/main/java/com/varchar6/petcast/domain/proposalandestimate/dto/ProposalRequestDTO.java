package com.varchar6.petcast.domain.proposalandestimate.dto;


import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.proposalandestimate.aggregate.ProposalStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalRequestDTO {
    private int id;
    private String content;
    private String location;
    private String time;
    private int cost;
    private ProposalStatus status;
    private String created_at;
    private String updated_at;
    private boolean active;
}

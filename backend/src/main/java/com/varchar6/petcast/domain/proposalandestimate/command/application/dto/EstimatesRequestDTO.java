package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus;
import lombok.*;
import org.springframework.stereotype.Component;


@Builder
@Getter
@Setter
public class EstimatesRequestDTO {
    private int expectedCost;
    private String createdAt;
    private int proposalId;
    private int companyId;

    public EstimatesRequestDTO () {
    }

    public EstimatesRequestDTO ( int expectedCost, String createdAt, int companyId, int proposalId ) {
        this.expectedCost = expectedCost;
        this.createdAt = createdAt;
        this.companyId = companyId;
        this.proposalId = proposalId;
    }

    public int getExpectedCost () {
        return expectedCost;
    }

    public void setExpectedCost ( int expectedCost ) {
        this.expectedCost = expectedCost;
    }

    public String getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt ( String createdAt ) {
        this.createdAt = createdAt;
    }

    public int getProposalId () {
        return proposalId;
    }

    public void setProposalId ( int proposalId ) {
        this.proposalId = proposalId;
    }

    public int getCompanyId () {
        return companyId;
    }

    public void setCompanyId ( int companyId ) {
        this.companyId = companyId;
    }

    @Override
    public String toString () {
        return "EstimatesRequestDTO{" +
                "expectedCost=" + expectedCost +
                ", createdAt='" + createdAt + '\'' +
                ", proposalId=" + proposalId +
                ", companyId=" + companyId +
                '}';
    }
}

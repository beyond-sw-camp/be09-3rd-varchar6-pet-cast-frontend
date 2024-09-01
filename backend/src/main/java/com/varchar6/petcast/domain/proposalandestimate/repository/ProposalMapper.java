package com.varchar6.petcast.domain.proposalandestimate.repository;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Proposal;

import java.util.List;

public interface ProposalMapper {
    List<Proposal> findAllProposalsByMemberId(int memberId);

    List<Proposal> findAllProposalsByCompanyId(int companyId);

    Proposal findProposalById(int proposalId);
}

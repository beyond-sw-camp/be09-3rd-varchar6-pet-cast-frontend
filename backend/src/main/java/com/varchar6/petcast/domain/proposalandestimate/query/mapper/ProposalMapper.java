package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;

import java.util.List;

public interface ProposalMapper {
    List<Proposals> findAllProposalsByMemberId(int memberId);

    List<Proposals> findAllProposalsByCompanyId(int companyId);

    Proposals findProposalById(int proposalId);
}

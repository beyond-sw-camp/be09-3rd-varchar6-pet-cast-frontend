package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;

import java.util.List;

public interface ProposalsMapper {
    List<Proposals> findAllProposalsByMemberId(int memberId);

    List<Proposals> findAllProposalsByCompanyId(int companyId);

    ProposalResponseDTO findProposalById( int proposalId);
}

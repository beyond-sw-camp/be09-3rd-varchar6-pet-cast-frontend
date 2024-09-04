package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;

import java.util.List;

public interface ProposalService {
    List<ProposalResponseDTO> findAllProposalsByMemberId ( int memberId );

    ProposalResponseDTO findProposalById ( int id );

    List<ProposalResponseDTO> findAll ();
}

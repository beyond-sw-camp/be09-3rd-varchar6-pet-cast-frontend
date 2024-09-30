package com.varchar6.petcast.serviceothers.domain.proposalandestimate.query.service;

import com.varchar6.petcast.serviceothers.domain.proposalandestimate.query.dto.ProposalResponseDTO;

import java.util.List;

public interface ProposalService {
    List<ProposalResponseDTO> findAllProposalsByMemberId ( int memberId );

    // 고객이 보낸 기획서 목록 조회
//    List<ProposalResponseDTO> findAllProposalsByMemberIdAndCateNo(int memberId, int categoryId);

    List<ProposalResponseDTO> findAll ();

    ProposalResponseDTO findProposalById ( int id );
}

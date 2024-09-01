package com.varchar6.petcast.domain.proposalandestimate.service;

import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalResponseDTO;

import java.util.List;

public interface ProposalService {
    // 고객이 본인이 작성한 기획서 목록 조회
    List<ProposalResponseDTO> findAllProposalsByMemberId(int memberId);

    // 업체가 고객한테 받은 기획서 목록 조회
    List<ProposalResponseDTO> findAllProposalsByCompanyId(int companyId);

    // 기획서 하나 상세 조회
    ProposalResponseDTO findProposalById(int proposalId);

    ProposalResponseDTO createProposal(ProposalRequestDTO proposalRequestDTO);

    void deleteProposal(int proposalId);
}

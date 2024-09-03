package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;

import java.util.List;

public interface ProposalsService {
    // 고객이 본인이 작성한 기획서 목록 조회
    List<ProposalsResponseDTO> findAllProposalsByMemberId(int memberId);

    // 업체가 고객한테 받은 기획서 목록 조회
    List<ProposalsResponseDTO> findAllProposalsByCompanyId(int companyId);

    // 기획서 하나 상세 조회
    ProposalsResponseDTO findProposalById(int proposalId);

    // 기획서 작성
    ProposalsResponseDTO createProposal(ProposalsRequestDTO proposalRequestDTO);

    // 기획서 삭제
    void deleteProposal(int proposalId);
}

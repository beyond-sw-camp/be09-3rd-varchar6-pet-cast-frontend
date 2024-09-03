package com.varchar6.petcast.domain.proposalandestimate.query.service;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.mapper.ProposalsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService{
        private final ProposalsMapper proposalsMapper;
        private final ProposalService proposalService;

        @Autowired
    public ProposalServiceImpl ( ProposalsMapper proposalsMapper, ProposalService proposalService ) {
        this.proposalsMapper = proposalsMapper;
        this.proposalService = proposalService;
    }

    // 고객이 보낸 기획서 목록 조회
        @Override
        public List<ProposalResponseDTO> findAllProposalsByMemberId( int memberId) {
            List<Proposals> proposals = proposalsMapper.findAllProposalsByMemberId(memberId);
            return proposals.stream().map(this::entityToResponseDTO).toList();
        }

        // 업체가 받은 기획서 목록 조회
        @Override
        public List<ProposalResponseDTO> findAllProposalsByCompanyId( int companyId) {
            List<Proposals> proposals = proposalsMapper.findAllProposalsByCompanyId(companyId);
            return proposals.stream().map(this::entityToResponseDTO).toList();
        }

        // 기획서 상세 조회
        @Override
        public ProposalResponseDTO findProposalById( int proposalId) {
            return proposalsMapper.findProposalById(proposalId);
        }

        private ProposalResponseDTO entityToResponseDTO( Proposals proposals) {
            return ProposalResponseDTO.builder()
                    .id ( proposals.getId () )
                    .hopeTime ( proposals.getHopeTime () )
                    .hopeLocation ( proposals.getHopeLocation () )
                    .hopeCost ( proposals.getHopeCost () )
                    .createdAt (proposals.getCreatedAt ())
                    .updatedAt (proposals.getUpdatedAt ())
                    .status ( proposals.getStatus () )
                    .active ( proposals.isActive () )
                    .build ();
        }
    }


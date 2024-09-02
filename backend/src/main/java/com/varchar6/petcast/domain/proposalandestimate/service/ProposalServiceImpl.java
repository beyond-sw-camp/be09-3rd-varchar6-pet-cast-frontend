package com.varchar6.petcast.domain.proposalandestimate.service;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Proposal;
import com.varchar6.petcast.domain.proposalandestimate.aggregate.ProposalStatus;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.repository.ProposalMapper;
import com.varchar6.petcast.domain.proposalandestimate.repository.ProposalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ProposalServiceImpl implements ProposalService{

    private final ProposalMapper proposalMapper;
    private final ProposalRepository proposalRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ProposalServiceImpl(ProposalMapper proposalMapper, ProposalRepository proposalRepository) {
        this.proposalMapper = proposalMapper;
        this.proposalRepository = proposalRepository;
    }

    // 고객 기획서 목록 조회
    @Override
    public List<ProposalResponseDTO> findAllProposalsByMemberId(int memberId) {
        List<Proposal> proposals = proposalMapper.findAllProposalsByMemberId(memberId);
        return proposals.stream().map(this::entityToResponseDTO).toList();
    }

    // 업체 기획서 목록 조회
    @Override
    public List<ProposalResponseDTO> findAllProposalsByCompanyId(int companyId) {
        List<Proposal> proposals = proposalMapper.findAllProposalsByCompanyId(companyId);
        return proposals.stream().map(this::entityToResponseDTO).toList();
    }

    // 기획서 상세 조회
    @Override
    public ProposalResponseDTO findProposalById(int proposalId) {
        Proposal proposal = proposalMapper.findProposalById(proposalId);
        if (proposal == null) {
            throw new IllegalArgumentException("해당 " + proposalId + " 번 기획서를 찾을 수 없습니다.");
        }
        return entityToResponseDTO(proposal);
    }

    // 기획서 작성
    @Transactional
    public ProposalResponseDTO createProposal(ProposalRequestDTO proposalRequestDTO) {
        Proposal proposal = proposalDTOToEntity(proposalRequestDTO);
        proposal = proposalRepository.save(proposal);
        return entityToResponseDTO(proposal);
    }

    // 기획서 삭제
    @Transactional
    public void deleteProposal(int proposalId) {
        proposalRepository.deleteById(proposalId);

        if (!proposalRepository.existsById(proposalId)) {
            throw new IllegalArgumentException("해당 " + proposalId + " 번 기획서를 찾을 수 없습니다.");
        }
        proposalRepository.save(proposalId);
    }

    private Proposal proposalDTOToEntity(ProposalRequestDTO proposalRequestDTO) {
        return Proposal.builder()
                .content(proposalRequestDTO.getContent())
                .location(proposalRequestDTO.getLocation())
                .time(proposalRequestDTO.getTime())
                .cost(proposalRequestDTO.getCost())
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .status(ProposalStatus.SENT)
                .active(true)
                .build();
    }

    private ProposalResponseDTO entityToResponseDTO(Proposal proposal) {
        return ProposalResponseDTO.builder()
                .id(proposal.getId())
                .location(proposal.getLocation())
                .time(proposal.getTime())
                .content(proposal.getContent())
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .status(proposal.getStatus())
                .cost(proposal.getCost())
                .active(proposal.isActive())
                .build();
    }
}

package com.varchar6.petcast.domain.proposalandestimate.controller;

import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.service.ProposalService;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proposal")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    // 기획서 작성
    @PostMapping
    public ResponseEntity<ProposalResponseDTO> createProposal(@RequestBody ProposalRequestDTO proposalRequestDTO) {
        ProposalResponseDTO createdProposal = proposalService.createProposal(proposalRequestDTO);
        return new ResponseEntity<>(createdProposal, HttpStatus.CREATED);
    }

    // 고객이 본인이 작성한 기획서 목록 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<ProposalResponseDTO>> getProposalsByMemberId(@PathVariable int memberId) {
        List<ProposalResponseDTO> proposals = proposalService.findAllProposalsByMemberId(memberId);
        return ResponseEntity.ok(proposals);
    }

    // 업체가 받은 기획서 목록 조회
    @GetMapping("/list/{companyId}")
    public ResponseEntity<List<ProposalResponseDTO>> getProposalsForCompany(@PathVariable int companyId) {
        List<ProposalResponseDTO> proposals = proposalService.findAllProposalsByCompanyId(companyId);
        return ResponseEntity.ok(proposals);
    }

    // 기획서 상세 조회
    @GetMapping("/list/{proposalId}")
    public ResponseEntity<ProposalResponseDTO> getProposalById(@PathVariable int proposalId) {
        ProposalResponseDTO proposal = proposalService.findProposalById(proposalId);
        return ResponseEntity.ok(proposal);
    }

    // 기획서 삭제
    @DeleteMapping("/list/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable int proposalId) {
        proposalService.deleteProposal(proposalId);
        return ResponseEntity.noContent().build();

    }
}

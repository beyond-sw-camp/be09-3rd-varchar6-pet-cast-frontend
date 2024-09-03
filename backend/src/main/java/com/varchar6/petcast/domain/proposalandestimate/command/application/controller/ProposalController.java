package com.varchar6.petcast.domain.proposalandestimate.command.application.controller;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.service.ProposalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/proposal")
public class ProposalController {

    private final ProposalsService proposalsService;

    @Autowired
    public ProposalController(ProposalsService proposalsService) {
        this.proposalsService = proposalsService;
    }

    // 기획서 작성
    @PostMapping
    public ResponseEntity<ProposalsResponseDTO> createProposal(@RequestBody ProposalsRequestDTO proposalRequestDTO) {
        ProposalsResponseDTO createdProposal = proposalsService.createProposal(proposalRequestDTO);
        return new ResponseEntity<>(createdProposal, HttpStatus.CREATED);
    }


    // 기획서 삭제
    @DeleteMapping("/list/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable int proposalId) {
        proposalsService.deleteProposal(proposalId);
        return ResponseEntity.noContent().build();

    }
}

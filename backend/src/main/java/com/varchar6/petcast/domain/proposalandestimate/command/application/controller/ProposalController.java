package com.varchar6.petcast.domain.proposalandestimate.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.ProposalResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.service.ProposalService;
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


    // 기획서 삭제
    @DeleteMapping("/list/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable int proposalId) {
        proposalService.deleteProposal(proposalId);
        return ResponseEntity.noContent().build();

    }
}

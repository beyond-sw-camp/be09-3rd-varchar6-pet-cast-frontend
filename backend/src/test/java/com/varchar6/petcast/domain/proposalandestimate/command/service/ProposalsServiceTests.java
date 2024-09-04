package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProposalsServiceTests {

    private final ProposalsService proposalsService;
    private ProposalsRequestDTO proposalsRequestDTO;
    private ProposalsResponseDTO proposalsResponseDTO;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ProposalsServiceTests ( ProposalsService proposalsService, ProposalsRequestDTO proposalsRequestDTO, ProposalsResponseDTO proposalsResponseDTO ) {
        this.proposalsService = proposalsService;
        this.proposalsRequestDTO = proposalsRequestDTO;
        this.proposalsResponseDTO = proposalsResponseDTO;
    }

    @Test
    @Transactional
    void 기획서_작성_테스트 () {
        proposalsResponseDTO.setContent ( "testContent" );
        proposalsResponseDTO.setHopeCost ( 50000 );
        proposalsResponseDTO.setHopeLocation ( "seoul" );
        proposalsResponseDTO.setHopeTime ( "2024-09-10T18:26:45" );
        ProposalsResponseDTO result = proposalsService.createProposal (proposalsRequestDTO);

        assertEquals ( 1,result );
    }

    @Test
    void 기획서_삭제_테스트 () {
    }
}
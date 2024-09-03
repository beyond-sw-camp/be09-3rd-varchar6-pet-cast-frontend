package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.EstimatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Slf4j
public class EstimatesServiceImpl implements EstimatesService {

    private final EstimatesRepository estimatesRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimatesServiceImpl(EstimatesRepository estimatesRepository) {
        this.estimatesRepository = estimatesRepository;
    }

    // 견적서 작성
    @Transactional
    public EstimatesResponseDTO createEstimate(EstimatesRequestDTO estimatesRequestDTO) {
        Estimates estimates = Estimates.builder()
                .expectedCost(estimatesRequestDTO.getExpectedCost())
                .status(estimatesRequestDTO.getStatus())
                .createdAt(LocalDateTime.now().format(FORMATTER))
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .companyId(estimatesRequestDTO.getCompanyId())
                .proposalId(estimatesRequestDTO.getProposalId())
                .build();

        EstimatesResponseDTO.builder()
                .expectedCost(estimates.getExpectedCost())
                .build();

        return entityToResponseDTO(estimates);

    }

    // 견적서 삭제

    @Transactional
    public void deleteEstimate(int estimateId) {
        if(estimatesRepository.existsById(estimateId)) {
            estimatesRepository.deleteById(estimateId);
        } else {
            throw new IllegalArgumentException("해당 견적서를 찾을 수 없습니다.");
        }
    }

    // 견적서 수락
    @Transactional
    public EstimatesResponseDTO acceptEstimate(int estimateId) {
        Estimates estimates = estimatesRepository.findById(estimateId).orElseThrow(IllegalArgumentException::new);

        estimates.accept();
        estimates = estimatesRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    // 견적서 거절
    @Transactional
    public EstimatesResponseDTO rejectEstimate(int estimateId) {
        Estimates estimates = estimatesRepository.findById(estimateId).orElseThrow(IllegalArgumentException::new);

        estimates.reject();
        estimates = estimatesRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    @Override
    public List<EstimatesResponseDTO> findAllEstimatesByMemberId(int memberId) {
        return List.of();
    }

    @Override
    public List<EstimatesResponseDTO> findAllEstimatesByCompanyId(int companyId) {
        return List.of();
    }

    @Override
    public EstimatesResponseDTO findEstimateById(int estimateId) {
        return null;
    }

    private EstimatesResponseDTO entityToResponseDTO(Estimates estimates) {
        return EstimatesResponseDTO.builder()
                .expectedCost(estimates.getExpectedCost())
                .build();
    }

}



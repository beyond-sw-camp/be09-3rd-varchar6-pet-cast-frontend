package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;

import java.util.List;

public interface EstimatesService {

    EstimatesResponseDTO createEstimate(EstimatesRequestDTO estimatesRequestDTO);

    void deleteEstimate(int estimateId);

    EstimatesResponseDTO acceptEstimate(int estimateId);

    EstimatesResponseDTO rejectEstimate(int estimateId);

    List<EstimatesResponseDTO> findAllEstimatesByMemberId(int memberId);

    List<EstimatesResponseDTO> findAllEstimatesByCompanyId(int companyId);

    EstimatesResponseDTO findEstimateById(int estimateId);
}

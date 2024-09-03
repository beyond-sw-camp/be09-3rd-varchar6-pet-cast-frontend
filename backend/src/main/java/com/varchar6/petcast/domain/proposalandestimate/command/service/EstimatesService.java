package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimateRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;

import java.util.List;

public interface EstimatesService {
    // 고객 견적서 목록 조회
    List<EstimatesResponseDTO> findAllEstimatesByMemberId(int memberId);

    // 업체 견적서 목록 조회
    List<EstimatesResponseDTO> findAllEstimatesByCompanyId(int companyId);

    // 견적서 상세 조회
    EstimatesResponseDTO findEstimateById(int estimateId);

    EstimatesResponseDTO createEstimate(EstimateRequestDTO estimateRequestDTO);

    void deleteEstimate(int estimateId);

    EstimatesResponseDTO acceptEstimate(int estimateId);

    EstimatesResponseDTO rejectEstimate(int estimateId);
}

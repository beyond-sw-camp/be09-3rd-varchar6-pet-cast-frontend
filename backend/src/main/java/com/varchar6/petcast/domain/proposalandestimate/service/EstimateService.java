package com.varchar6.petcast.domain.proposalandestimate.service;

import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateResponseDTO;

import java.util.List;

public interface EstimateService {
    // 고객 견적서 목록 조회
    List<EstimateResponseDTO> findAllEstimatesByMemberId(int memberId);

    // 업체 견적서 목록 조회
    List<EstimateResponseDTO> findAllEstimatesByCompanyId(int companyId);

    // 견적서 상세 조회
    EstimateResponseDTO findEstimateById(int estimateId);
}

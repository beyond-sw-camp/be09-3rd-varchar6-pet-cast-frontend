package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateResponseDTO;

import java.util.List;

public interface EstimateService {
    List<EstimateResponseDTO> findAllEstimatesByMemberId ( int memberId );

    List<EstimateResponseDTO> findAllEstimatesByCompanyId ( int companyId );

    EstimateResponseDTO findEstimateById ( int estimateId );
}

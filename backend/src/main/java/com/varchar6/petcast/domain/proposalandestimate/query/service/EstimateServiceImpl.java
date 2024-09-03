package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.EstimatesRepository;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.mapper.EstimatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstimateServiceImpl implements EstimateService{

    private final EstimatesMapper estimatesMapper;
    private final EstimatesRepository estimatesRepository;

    @Autowired
    public EstimateServiceImpl ( EstimatesMapper estimatesMapper, EstimatesRepository estimatesRepository ) {
        this.estimatesMapper = estimatesMapper;
        this.estimatesRepository = estimatesRepository;
    }

    // 고객이 보낸 견적서 목록 조회
    @Override
    public List<EstimateResponseDTO> findAllEstimatesByMemberId( int memberId) {
        List<Estimates> estimates = estimatesMapper.findAllEstimatesByMemberId(memberId);
        return estimates.stream().map(this::entityToResponseDTO).toList();
    }

    // 업체가 받은 견적서 목록 조회
    @Override
    public List<EstimateResponseDTO> findAllEstimatesByCompanyId( int companyId) {
        List<Estimates> estimates = estimatesMapper.findAllEstimatesByCompanyId(companyId);
        return estimates.stream().map(this::entityToResponseDTO).toList();
    }

    // 견적서 상세 조회
    @Override
    public EstimateResponseDTO findEstimateById( int estimateId) {
        return estimatesMapper.findEstimateById(estimateId);
    }

    private EstimateResponseDTO entityToResponseDTO( Estimates estimates) {
        return EstimateResponseDTO.builder()
                .id ( estimates.getId () )
                .expectedCost(estimates.getExpectedCost())
                .createdAt (estimates.getCreatedAt ())
                .updatedAt (estimates.getUpdatedAt ())
                .status ( estimates.getStatus () )
                .active ( estimates.isActive () )
                .build ();
    }
}

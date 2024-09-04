package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstimatesMapper {
    List<Estimates> findAllEstimatesByMemberId(int memberId);

    List<Estimates> findAllEstimatesByCompanyId(int companyId);

    EstimateResponseDTO findEstimateById( int estimateId);
}

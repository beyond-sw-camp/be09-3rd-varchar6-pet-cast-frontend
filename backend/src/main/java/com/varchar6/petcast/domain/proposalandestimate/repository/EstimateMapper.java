package com.varchar6.petcast.domain.proposalandestimate.repository;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Estimate;

import java.util.List;

public interface EstimateMapper {
    List<Estimate> findAllEstimatesByMemberId(int memberId);

    List<Estimate> findAllEstimatesByCompanyId(int companyId);

    Estimate findEstimateById(int estimateId);
}

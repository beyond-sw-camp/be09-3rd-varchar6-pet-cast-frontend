package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;

import java.util.List;

public interface EstimateMapper {
    List<Estimates> findAllEstimatesByMemberId(int memberId);

    List<Estimates> findAllEstimatesByCompanyId(int companyId);

    Estimates findEstimateById(int estimateId);
}

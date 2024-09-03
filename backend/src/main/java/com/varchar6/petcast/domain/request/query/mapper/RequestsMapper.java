package com.varchar6.petcast.domain.request.query.mapper;

import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Requests;

import java.util.List;

public interface RequestsMapper {
    List<Requests> findAllRequestsByMemberId(int memberId);

    List<Requests> findAllRequestsByCompanyId(int companyId);

    Requests findRequestById(int requestId);
}
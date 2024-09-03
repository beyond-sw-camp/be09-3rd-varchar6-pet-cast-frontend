package com.varchar6.petcast.domain.request.query.service;

import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;

import java.util.List;

public interface RequestsService {

    List<MemberAndRequestDTO> findAllRequestsByMemberId(int memberId);

    List<CompanyAndRequestDTO> findAllRequestsByCompanyId(int companyId);

    RequestDetailDTO findRequestById(int requestId);

    List<String> findCategoryList();
}

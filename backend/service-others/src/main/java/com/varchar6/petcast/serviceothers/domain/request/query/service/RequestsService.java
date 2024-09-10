package com.varchar6.petcast.serviceothers.domain.request.query.service;

import com.varchar6.petcast.serviceothers.domain.request.query.dto.*;

import java.util.List;

public interface RequestsService {

    RequestCategoryResponseDTO findCategoryById(int categoryId);

    List<MemberAndRequestDTO> findAllRequestsByMemberId(int userId);

    List<CompanyAndRequestDTO> findAllRequestsByCompanyId(int companyId);

    RequestDTO findRequestById(int requestId);
}

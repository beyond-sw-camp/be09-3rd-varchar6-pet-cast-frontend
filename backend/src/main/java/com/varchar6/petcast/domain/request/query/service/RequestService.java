package com.varchar6.petcast.domain.request.query.service;

import com.varchar6.petcast.domain.request.command.application.dto.response.CreateRequestsResponseDTO;

import java.util.List;

public class RequestService {
    public List<CreateRequestsResponseDTO> findAllRequestsByMemberId ( int memberId ) {
        return List.of ();
    }

    public List<CreateRequestsResponseDTO> findAllRequestsByCompanyId ( int companyId ) {
        return List.of ();

    }

    public CreateRequestsResponseDTO findRequestById ( int requestId ) {
        return null;
    }
}

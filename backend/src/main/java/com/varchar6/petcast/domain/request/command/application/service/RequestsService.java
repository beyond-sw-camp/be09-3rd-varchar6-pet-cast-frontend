package com.varchar6.petcast.domain.request.command.application.service;

import com.varchar6.petcast.domain.request.command.application.dto.RequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.application.dto.RequestsResponseDTO;
import com.varchar6.petcast.domain.request.dto.RequestRequestDTO;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;

import java.util.List;

public interface RequestsService {
    // 요청서 작성
    RequestsResponseDTO createRequest(RequestsRequestDTO requestRequestDTO);

    //  요청서 삭제
    void deleteRequest(int requestId);

    // 요청서 수락
    RequestsResponseDTO acceptRequest(int requestId);

    // 요청서 거절
    RequestsResponseDTO rejectRequest(int requestId);
}
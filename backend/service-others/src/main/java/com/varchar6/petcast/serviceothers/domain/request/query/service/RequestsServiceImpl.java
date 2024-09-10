package com.varchar6.petcast.serviceothers.domain.request.query.service;

import com.varchar6.petcast.serviceothers.domain.request.query.dto.*;
import com.varchar6.petcast.serviceothers.domain.request.query.mapper.RequestsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value = "queryRequestServiceImpl")
public class RequestsServiceImpl implements RequestsService{

    private final RequestsMapper requestsMapper;

    @Autowired
    public RequestsServiceImpl(RequestsMapper requestsMapper) {
        this.requestsMapper = requestsMapper;
    }

    @Override
    @Transactional
    public RequestCategoryResponseDTO findCategoryById(int categoryId) {
        RequestCategoryResponseDTO requestCategoryResponseDTO = new RequestCategoryResponseDTO();
        requestCategoryResponseDTO.setCategoryId(categoryId);

        return requestsMapper.selectCategoryList(categoryId);
    }

    @Override
    @Transactional
    public List<MemberAndRequestDTO> findAllRequestsByMemberId(int userId) {
        return requestsMapper.selectAllRequestsByMemberId(userId).stream().toList();
    }

    @Override
    @Transactional
    public List<CompanyAndRequestDTO> findAllRequestsByCompanyId(int companyId) {
        return requestsMapper.selectAllRequestsByCompanyId(companyId).stream().toList();
    }

    @Override
    @Transactional
    public RequestDTO findRequestById(int requestId) {
        return requestsMapper.selectRequestById(requestId);
    }
}

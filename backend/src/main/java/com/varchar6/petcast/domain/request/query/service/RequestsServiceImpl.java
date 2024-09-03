package com.varchar6.petcast.domain.request.query.service;

import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;
import com.varchar6.petcast.domain.request.query.mapper.RequestsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "queryRequestServiceImpl")
public class RequestsServiceImpl implements RequestsService{

    private final RequestsMapper requestsMapper;

    @Autowired
    public RequestsServiceImpl(RequestsMapper requestsMapper) {
        this.requestsMapper = requestsMapper;
    }

    @Override
    public List<String> findCategoryList() {
        return requestsMapper.selectCategoryList().stream().toList();
    }

    @Override
    public List<MemberAndRequestDTO> findAllRequestsByMemberId(int memberId) {
        return requestsMapper.selectAllRequestsByMemberId(memberId).stream().toList();
    }

    @Override
    public List<CompanyAndRequestDTO> findAllRequestsByCompanyId(int companyId) {
        return requestsMapper.selectAllRequestsByCompanyId(companyId).stream().toList();
    }

    @Override
    public RequestDetailDTO findRequestById(int requestId) {
        return requestsMapper.selectRequestById(requestId);
    }
}

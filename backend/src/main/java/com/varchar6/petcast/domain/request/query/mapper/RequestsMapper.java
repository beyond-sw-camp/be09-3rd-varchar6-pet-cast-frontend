package com.varchar6.petcast.domain.request.query.mapper;

import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestsMapper {
    List<String> selectCategoryList();

    List<MemberAndRequestDTO> selectAllRequestsByMemberId(int memberId);

    List<CompanyAndRequestDTO> selectAllRequestsByCompanyId(int companyId);

    RequestDetailDTO selectRequestById(int requestId);
}
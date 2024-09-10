package com.varchar6.petcast.serviceothers.domain.request.query.mapper;

import com.varchar6.petcast.serviceothers.domain.request.query.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequestsMapper {
   RequestCategoryResponseDTO selectCategoryList(@Param("category_id")int category_id);

    List<MemberAndRequestDTO> selectAllRequestsByMemberId(@Param("user_id")int userId);

    List<CompanyAndRequestDTO> selectAllRequestsByCompanyId(@Param("company_id") int companyId);

    RequestDTO selectRequestById(@Param("request_id") int requestId);
}
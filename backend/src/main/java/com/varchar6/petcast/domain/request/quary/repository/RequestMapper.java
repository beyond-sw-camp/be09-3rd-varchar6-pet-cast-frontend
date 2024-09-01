package com.varchar6.petcast.domain.request.quary.repository;

import com.varchar6.petcast.domain.request.quary.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestMapper {
    List<RequestDTO> findAllRequests();

    RequestDTO findRequestById(int id);
}

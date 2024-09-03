package com.varchar6.petcast.domain.company.query.mapper;

import com.varchar6.petcast.domain.company.query.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    List<CategoryDTO> findCategoriesByCompanyId(String companyId);
}

package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.dto.CategoryResponseDTO;
import com.varchar6.petcast.domain.company.query.dto.response.CompanyResponseDTO;


public interface CompanyService {
    CategoryResponseDTO getCategories(String companyId);
    CompanyResponseDTO getCompanyInformationByCompanyId(int companyId);
}

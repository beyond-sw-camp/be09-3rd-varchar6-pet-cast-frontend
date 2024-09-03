package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.dto.CategoryResponseDTO;


public interface CompanyService {
    CategoryResponseDTO getCategories(String companyId);
}

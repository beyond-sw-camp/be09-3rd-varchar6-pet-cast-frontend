package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.dto.CategoryDTO;

import java.util.List;

public interface CompanyService {
    List<String> getCategories(String companyId);
}

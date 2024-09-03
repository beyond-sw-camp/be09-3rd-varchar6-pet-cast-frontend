package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.command.domain.repository.CompanyRepository;
import com.varchar6.petcast.domain.company.query.dto.CategoryDTO;
import com.varchar6.petcast.domain.company.query.mapper.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public List<CategoryDTO> getCategories(String companyId) {
        List<CategoryDTO> categoryList = companyMapper.findCategoriesByCompanyId(companyId);
        log.debug("categoryList: {}", categoryList);

        return List.of();
    }
}

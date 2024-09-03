package com.varchar6.petcast.domain.company.command.application;

import com.varchar6.petcast.domain.company.command.domain.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service(value="commandCompanyServiceImpl")
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}

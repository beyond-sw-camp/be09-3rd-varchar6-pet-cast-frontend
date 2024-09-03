package com.varchar6.petcast.domain.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyEnrollRequestDTO;
import com.varchar6.petcast.domain.company.command.application.dto.response.CompanyResponseDTO;
import com.varchar6.petcast.domain.company.command.domain.aggregate.Company;
import com.varchar6.petcast.domain.company.command.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service(value="commandCompanyServiceImpl")
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyResponseDTO applyEnroll(CompanyEnrollRequestDTO companyEnrollRequestDTO) {
        return toResponseDTO(
                companyRepository.save(
                        toEntity(companyEnrollRequestDTO)
                )
        );
    }


    private Company toEntity(CompanyEnrollRequestDTO companyEnrollRequestDTO) {
        return Company.builder()
                .name(companyEnrollRequestDTO.getName())
                .address(companyEnrollRequestDTO.getAddress())
                .employeeNumber(companyEnrollRequestDTO.getEmployeeNumber())
                .career(companyEnrollRequestDTO.getCareer())
                .license(companyEnrollRequestDTO.getLicense())
                .introduction(companyEnrollRequestDTO.getIntroduction())
                .contactableTime(companyEnrollRequestDTO.getContactableTime())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .approved(false)
                .memberId(companyEnrollRequestDTO.getMemberId())
                .build();
    }

    private CompanyResponseDTO toResponseDTO(Company company) {
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setId(company.getId());
        companyResponseDTO.setName(company.getName());
        companyResponseDTO.setAddress(company.getAddress());
        companyResponseDTO.setEmployeeNumber(company.getEmployeeNumber());
        companyResponseDTO.setCareer(company.getCareer());
        companyResponseDTO.setLicense(company.getLicense());
        companyResponseDTO.setIntroduction(company.getIntroduction());
        companyResponseDTO.setContactableTime(company.getContactableTime());
        companyResponseDTO.setCreatedAt(company.getCreatedAt());
        companyResponseDTO.setUpdatedAt(company.getUpdatedAt());
        companyResponseDTO.setActive(company.isActive());
        companyResponseDTO.setApproved(company.isApproved());
        companyResponseDTO.setMemberId(company.getMemberId());


        return companyResponseDTO;
    }
}

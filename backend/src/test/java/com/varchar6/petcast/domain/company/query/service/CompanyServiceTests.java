package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CompanyServiceTests {
    @Autowired
    private CompanyService companyService;

    @Test
    public void 카테고리_조회_테스트() {
        List<String> categoryList = companyService.getCategories("1");
        categoryList.forEach(System.out::println);
    }


}
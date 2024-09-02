package com.varchar6.petcast.domain.qna.command.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="company-service", url="localhost:8000")
public interface CompanyClient {

//    @GetMapping("/api/v1/compnay/{companyId}")
//    CompanyResp
}

package com.varchar6.petcast.serviceothers.domain.notice.command.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;

@FeignClient(name="petcast-member-service", url="localhost:8000", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface MemberServiceClient {
//    List<ResponseRole>
}

package com.varchar6.petcast.serviceothers.infrastructure.client;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.notice.command.domain.aggregate.vo.ResponseMemberRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="petcast-member-service", url="localhost:8000", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface MemberServiceClient {

    @GetMapping("/service-member/api/v1/members/search-member-role")
    ResponseEntity<ResponseMessage> searchMemberRole(Map<String, String> map);
}

package com.varchar6.petcast.serviceothers.domain.notice.command.infrastructure;

import com.varchar6.petcast.serviceothers.domain.notice.command.domain.aggregate.ResponseMemberRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.List;

@FeignClient(name="petcast-member-service", url="localhost:8000", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface MemberServiceClient {

    @GetMapping("member-service/search-member-role")
    List<ResponseMemberRole> searchMemberRole(@RequestAttribute("memberId") int id);
}

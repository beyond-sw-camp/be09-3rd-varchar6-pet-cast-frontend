package com.varchar6.petcast.serviceothers.infrastructure.service;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.infrastructure.client.MemberServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "InfrastructureMemberService")
public class MemberServiceImpl implements MemberService {
    private final MemberServiceClient memberServiceClient;

    @Autowired
    public MemberServiceImpl(MemberServiceClient memberServiceClient) {
        this.memberServiceClient = memberServiceClient;
    }

    @Override
    public List<String> checkMemberRole(Map<String, String> map) throws IllegalAccessException {
        List<String> RequestRoleList = new ArrayList<String>();
        ResponseMessage messageBody = memberServiceClient.searchMemberRole(map).getBody();
        /* 필기. 타입 변환이 가능한지 확인(rest api를 통해서) */
        if(messageBody instanceof ResponseMessage) {
            if (messageBody.getResult() instanceof List) {
                List test = (List) messageBody.getResult();
                Map<String, String> roleList = new HashMap<>();

                for (Object getRole : test) {

                    roleList = (Map<String, String>) getRole;

                    RequestRoleList.add((String) roleList.get("name"));
                }
            }
            else{
                throw new ClassCastException("message.getBody().getResult()는 리스트가 아닙니다.");
            }
        }
        else{
            throw new ClassCastException("feign client 오류입니다.");
        }
        return RequestRoleList;
    }
}
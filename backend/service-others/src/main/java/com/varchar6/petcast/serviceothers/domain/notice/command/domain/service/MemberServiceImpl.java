package com.varchar6.petcast.serviceothers.domain.notice.command.domain.service;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.infrastructure.client.MemberServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "commandNoticeMemberService")
public class MemberServiceImpl implements MemberService {
    MemberServiceClient memberServiceClient;

    @Autowired
    public MemberServiceImpl(MemberServiceClient memberServiceClient) {
        this.memberServiceClient = memberServiceClient;
    }

    @Override
    public boolean checkMemberRole(Map<String, String> map) throws IllegalAccessException {
        boolean flag = false;

        if(memberServiceClient.searchMemberRole(map) instanceof ResponseMessage) {
            ResponseEntity<ResponseMessage> message = (ResponseEntity<ResponseMessage>) memberServiceClient.searchMemberRole(map);
            if (message.getBody().getResult() instanceof List) {
                List test = (List) message.getBody().getResult();

                Map<String, String> roleList = new HashMap<>();
                // Message 객체가 Map<String, Object> 형태라면 캐스팅 후 접근

                for (Object getRole : test) {

                    roleList = (Map<String, String>) getRole;

                    String roleCheck = (String) roleList.get("name");

//                    List<String>으로 역할들을 받는다. 반환값을 List<String>으로 변환

                    if (roleCheck.equals("ROLE_ADMIN")) {
                        flag = true;
                        break;
                    }
                }
            }
            else{
                throw new ClassCastException("message.getBody().getResult()는 리스트가 아닙니다.");
            }
        }
        else{
            throw new ClassCastException("feign client 오류입니다.");
        }
        if(!flag)
            throw new IllegalAccessException("관리자가 아닙니다.");

        return flag;
    }
}

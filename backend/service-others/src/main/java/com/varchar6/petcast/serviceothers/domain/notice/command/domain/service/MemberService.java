package com.varchar6.petcast.serviceothers.domain.notice.command.domain.service;

import java.util.Map;

public interface MemberService {
    boolean checkMemberRole(Map<String, String> map) throws IllegalAccessException;
}

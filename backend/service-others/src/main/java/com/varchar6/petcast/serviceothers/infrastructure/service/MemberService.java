package com.varchar6.petcast.serviceothers.infrastructure.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
    List<String> checkMemberRole(Map<String, String> map) throws IllegalAccessException;
}

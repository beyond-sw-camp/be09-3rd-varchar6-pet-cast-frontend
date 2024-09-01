package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.mapper.GatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GatherService {

    public List<GatherDTO> findAllGather(int userId);

    public GatherDetailDTO findDetailGather(int gatherId);

    public Boolean isAccessTrueGather(int invitationId, int userId);

    public List<String> findGroupMemberById(int gatherId);
}

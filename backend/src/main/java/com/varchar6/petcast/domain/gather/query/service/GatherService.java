package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.mapper.GatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GatherService {

    private GatherMapper gatherMapper;

    @Autowired
    public GatherService(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }

    @Transactional
    public List<GatherDTO> findAllGather(int userId) {
        return gatherMapper.selectGatherById(userId);
    }
}

package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
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

    @Transactional
    public GatherDetailDTO findDetailGather(int gatherId) {

        GatherDTO gatherInfo = gatherMapper.selectGatherDetailById(gatherId);
        List<String> memberInfo = gatherMapper.selectMembersById(gatherId);

        GatherDetailDTO gatherDetail = GatherDetailDTO.builder()
                .id(gatherInfo.getId())
                .name(gatherInfo.getName())
                .content(gatherInfo.getContent())
                .url(gatherInfo.getUrl())
                .updatedAt(gatherInfo.getUpdatedAt())
                .createdAt(gatherInfo.getCreatedAt())
                .activeYn(gatherInfo.isActiveYn())
                .invitationId(gatherInfo.getInvitationId())
                .intitationContent(gatherInfo.getIntitationContent())
                .members(memberInfo)
                .build();

        return gatherDetail;
    }

    public Boolean isAccessTrueGather(int invitationId, int userId) {
        List<Integer> membersId = gatherMapper.selectGroupMemberMemberById(invitationId, userId);
        if(membersId.contains(userId)) {
            return true;
        }
        return false;
    }
}

package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.mapper.GatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value="queryGatherServiceImpl")
public class GatherServiceImpl implements GatherService{

    private GatherMapper gatherMapper;

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }

    public List<GatherDTO> findAllGather(int userId) {
        return gatherMapper.selectGatherById(userId);
    }

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
        List<Integer> membersId = gatherMapper.selectGroupMembersIdById(invitationId, userId);
        if (!membersId.isEmpty() && membersId.contains(userId)) {
            return true;
        }
        return false;
    }

    public List<String> findGroupMemberById(int gatherId) {
        List<String> groupMembers = gatherMapper.selectGroupMembersNameById(gatherId);
        return groupMembers;
    }
}

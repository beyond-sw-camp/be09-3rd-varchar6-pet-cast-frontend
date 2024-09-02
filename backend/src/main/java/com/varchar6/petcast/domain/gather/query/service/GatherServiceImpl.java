package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.mapper.GatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="queryGatherServiceImpl")
public class GatherServiceImpl implements GatherService{

    private GatherMapper gatherMapper;

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }

    public List<String> findAllGather(int userId) {
        List<GatherDTO> result =  gatherMapper.selectGatherById(userId);
        List<String> listOfNames = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            listOfNames.add(result.get(i).getName());
        }
        return listOfNames;
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
                .active(gatherInfo.isActive())
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

package com.varchar6.petcast.domain.gather.query.mapper;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GatherMapper {
    List<GatherDTO> selectGatherById(int userId);

    GatherDTO selectGatherDetailById(int gatherId);

    List<String> selectMembersById(int gatherId);
}

package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.CreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Gather;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherMemberRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value="commandGatherServiceImpl")
public class GatherServiceImpl implements GatherService{

    private final GatherRepository gatherRepository;
    private final GatherMemberRepository gatherMemberRepository;

    @Autowired
    public GatherServiceImpl(GatherRepository gatherRepository, GatherMemberRepository gatherMemberRepository) {
        this.gatherRepository = gatherRepository;
        this.gatherMemberRepository = gatherMemberRepository;
    }

    @Override
    @Transactional
    public void createGather(CreateGatherDTO createGatherDTO) {

        // 모임 테이블 insert 과정
        Gather gather = Gather.builder()
                .name(createGatherDTO.getName())
                .content(createGatherDTO.getContent())
                .number(createGatherDTO.getNumber())
                .url(createGatherDTO.getUrl())
                .updatedAt(createGatherDTO.getCreatedAt())
                .createdAt(createGatherDTO.getCreatedAt())
                .active(createGatherDTO.isActive())
                .invitationId(createGatherDTO.getInvitationId())
                .invitationContent(createGatherDTO.getInvitationContent())
                .build();

        Gather newGather = gatherRepository.save(gather);

        log.info("모임 테이블 성공~");

        // 모임&회원 중간 테이블 insert 과정
        GatherMember newGatherMember = GatherMember.builder()
                .role(GatherRole.LEADER)
                .memberId(createGatherDTO.getUserId())
                .gatherId(newGather.getId())
                .build();

//        GatherMember newGatherMemberId = gatherMemberRepository.save(newGatherMember);
        gatherMemberRepository.save(newGatherMember);

        log.info("모임회원 중간 테이블 성공~");

        // return 해서 pk 값 정보를 반환 할 수도 있다.
    }
}

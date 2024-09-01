package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestCreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestDeactiveGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestUpdateGatherInfoDTO;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Gather;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMemberFK;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherMemberRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

@Slf4j
@Service(value="commandGatherServiceImpl")
public class GatherServiceImpl implements GatherService{

    private final GatherRepository gatherRepository;
    private final GatherMemberRepository gatherMemberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GatherServiceImpl(GatherRepository gatherRepository, GatherMemberRepository gatherMemberRepository, ModelMapper modelMapper) {
        this.gatherRepository = gatherRepository;
        this.gatherMemberRepository = gatherMemberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void createGather(RequestCreateGatherDTO requestCreateGatherDTO) {

        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);

        log.info("current date: {}", currentDate);

        // 모임 테이블 insert 과정
        Gather gather = Gather.builder()
                .name(requestCreateGatherDTO.getName())
                .content(requestCreateGatherDTO.getContent())
                .number(requestCreateGatherDTO.getNumber())
                .url(requestCreateGatherDTO.getUrl())
                .updatedAt(currentDate)
                .createdAt(currentDate)
                .active(requestCreateGatherDTO.isActive())
                .invitationId(requestCreateGatherDTO.getInvitationId())
                .invitationContent(requestCreateGatherDTO.getInvitationContent())
                .build();

        Gather newGather = gatherRepository.save(gather);

        log.info("모임 테이블 성공~");

        // 모임&회원 중간 테이블 insert 과정
        // 복합키 설정
        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestCreateGatherDTO.getUserId())
                .gatherId(newGather.getId())
                .build();

        GatherMember newGatherMember = GatherMember.builder()
                .role(GatherRole.LEADER)
                .gatherMemberPK(gatherMemberPK)
                .build();

        gatherMemberRepository.save(newGatherMember);

        log.info("모임회원 중간 테이블 성공~");
    }

    @Override
    @Transactional
    public void updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);
        log.info("current date: {}", currentDate);
        // 모임의 LEADER인지 확인
        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestUpdateGatherDTO.getUserId())
                .gatherId(requestUpdateGatherDTO.getGatherId())
                .build();

        GatherMember checkMemberRole = gatherMemberRepository.findById(gatherMemberPK).orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberPK));
        log.info("해당 회원 역할 출력 {}", checkMemberRole);


        // 모임 수정
        if(checkMemberRole.getRole() == GatherRole.LEADER){
            Gather existGather = gatherRepository.findById(requestUpdateGatherDTO.getUserId()).orElseThrow();

            Gather updateGather = Gather.builder()
                    .name(requestUpdateGatherDTO.getName())
                    .content(requestUpdateGatherDTO.getContent())
                    .number(requestUpdateGatherDTO.getNumber())
                    .url(requestUpdateGatherDTO.getUrl())
                    .updatedAt(currentDate)
                    .createdAt(existGather.getCreatedAt())
                    .active(true)
                    .invitationId(requestUpdateGatherDTO.getInvitationId())
                    .invitationContent(requestUpdateGatherDTO.getInvitationContent())
                    .build();
            log.info("정보 저장 전~");
            gatherRepository.save(updateGather);

            log.info("정보 저장 성공!");
        }

    }

    @Override
    public void deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);

        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestDeactiveGatherDTO.getUserId())
                .gatherId(requestDeactiveGatherDTO.getGatherId())
                .build();

        GatherMember checkMemberRole = gatherMemberRepository.findById(gatherMemberPK).orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberPK));

        if(checkMemberRole.getRole() == GatherRole.LEADER){
            Gather currentGather = gatherRepository.findById(requestDeactiveGatherDTO.getUserId()).orElseThrow();

            Gather deactiveGather = Gather.builder()
                    .name(currentGather.getName())
                    .content(currentGather.getContent())
                    .number(currentGather.getNumber())
                    .url(currentGather.getUrl())
                    .updatedAt(currentDate)
                    .createdAt(currentGather.getCreatedAt())
                    .active(false)
                    .invitationId(currentGather.getInvitationId())
                    .invitationContent(currentGather.getInvitationContent())
                    .build();
            gatherRepository.save(deactiveGather);
        }
    }
}

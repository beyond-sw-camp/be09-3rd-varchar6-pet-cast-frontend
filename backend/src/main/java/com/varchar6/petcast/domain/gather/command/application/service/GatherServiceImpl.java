package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.domain.gather.command.application.dto.response.*;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Gather;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMemberFK;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Invitation;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherMemberRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.InvitationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

@Slf4j
@Service(value = "commandGatherServiceImpl")
public class GatherServiceImpl implements GatherService {

    private final GatherRepository gatherRepository;
    private final GatherMemberRepository gatherMemberRepository;
    private final InvitationRepository invitationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GatherServiceImpl(GatherRepository gatherRepository, GatherMemberRepository gatherMemberRepository, InvitationRepository invitationRepository, ModelMapper modelMapper) {
        this.gatherRepository = gatherRepository;
        this.gatherMemberRepository = gatherMemberRepository;
        this.invitationRepository = invitationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void createGather(RequestCreateGatherDTO requestCreateGatherDTO) {

        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);

        // 모임 테이블 insert 과정
        Gather gather = Gather.builder()
                .name(requestCreateGatherDTO.getName())
                .content(requestCreateGatherDTO.getContent())
                .number(requestCreateGatherDTO.getNumber())
                .url(requestCreateGatherDTO.getUrl())
                .updatedAt(currentDate)
                .createdAt(currentDate)
                .active(true)
                .invitationId(requestCreateGatherDTO.getInvitationId())
                .invitationContent(requestCreateGatherDTO.getInvitationContent())
                .build();
        Gather newGather = null;
        try {
            newGather = gatherRepository.save(gather);
        } catch (Exception e) {
            log.error("새로운 모임 insert 실패!!");
        }

        // 모임&회원 중간 테이블 insert 과정
        // 복합키 설정
        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestCreateGatherDTO.getUserId())
                .gatherId(newGather.getId())
                .build();

        GatherMember newGatherMember = GatherMember.builder()
                .role(GatherRole.LEADER)
                .gatherMemberFK(gatherMemberPK)
                .build();
        try {
            gatherMemberRepository.save(newGatherMember);
        } catch (Exception e) {
            log.error("모임&회원 중간 테이블 insert 실패!!");
        }
    }

    @Override
    @Transactional
    public ResponseUpdateGatherInfoDTO updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);

        // 모임의 LEADER인지 확인
        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestUpdateGatherDTO.getUserId())
                .gatherId(requestUpdateGatherDTO.getGatherId())
                .build();

        // 역할 검사
        GatherMember checkMemberRole = gatherMemberRepository.findById(gatherMemberPK).orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberPK));

        // 모임 수정
        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = null;
        if (checkMemberRole.getRole() == GatherRole.LEADER) {
            Gather updateGather = gatherRepository.findById(requestUpdateGatherDTO.getGatherId()).orElseThrow();
            updateGather.setName(requestUpdateGatherDTO.getName());
            updateGather.setContent(requestUpdateGatherDTO.getContent());
            updateGather.setNumber(requestUpdateGatherDTO.getNumber());
            updateGather.setUpdatedAt(currentDate);
            try {
                responseUpdateGatherInfoDTO = modelMapper.map(updateGather, ResponseUpdateGatherInfoDTO.class);
            } catch (Exception e) {
                log.error("모임 정보 수정 중에 에러 발생!!");
            }
        }
        return responseUpdateGatherInfoDTO;
    }

    @Override
    @Transactional
    public ResponseDeactiveGatherDTO deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDate = simpleDateFormat.format(now);

        GatherMemberFK gatherMemberPK = GatherMemberFK.builder()
                .memberId(requestDeactiveGatherDTO.getUserId())
                .gatherId(requestDeactiveGatherDTO.getGatherId())
                .build();

        GatherMember checkMemberRole = gatherMemberRepository.findById(gatherMemberPK).orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberPK));
        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = null;
        if (checkMemberRole.getRole() == GatherRole.LEADER) {
            Gather currentGather = gatherRepository.findById(requestDeactiveGatherDTO.getGatherId()).orElseThrow();
            currentGather.setActive(false);
            currentGather.setUpdatedAt(currentDate);

            try {
                responseDeactiveGatherDTO = modelMapper.map(currentGather, ResponseDeactiveGatherDTO.class);
            } catch (Exception e) {
                log.error("비활성화 업데이트 중 에러 발생!!");
            }

        }
        return responseDeactiveGatherDTO;
    }

    @Override
    @Transactional
    public ResponseSendInvitaionDTO sendInvitation(RequestSendInvitationDTO requestInvitationDTO) {

        // 1. 해당 모임의 모임장인지 확인
        GatherMemberFK gatherMemberFK = GatherMemberFK.builder()
                .memberId(requestInvitationDTO.getUserId())
                .gatherId(requestInvitationDTO.getGatherId())
                .build();

        GatherMember checkMemberRole = gatherMemberRepository.findById(gatherMemberFK).orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberFK));

        // 2. insert
        if (checkMemberRole.getRole() == GatherRole.LEADER) {
            java.util.Date now = new java.util.Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String currentDate = simpleDateFormat.format(now);

            // 2-1. 모임에 초대장 내용 update
            Gather existGather = gatherRepository.findById(requestInvitationDTO.getGatherId())
                    .orElseThrow(() -> new NoSuchElementException("GatherMember not found with id: " + gatherMemberFK));
            existGather.setInvitationId(requestInvitationDTO.getInvitationId());
            existGather.setInvitationContent(requestInvitationDTO.getInvitationContent());
            existGather.setUpdatedAt(currentDate);

            try {
                modelMapper.map(existGather, Gather.class);
            } catch (Exception e) {
                log.error("헤당 모임에 정보 넣다가 에러 발생!!");
            }

            // 2-2. 초대장 테이블에 insert
            Invitation invitation = Invitation.builder()
                    .activeYn(true)
                    .createdAt(currentDate)
                    .userId(gatherMemberFK.getMemberId())
                    .gatherId(gatherMemberFK.getGatherId())
                    .build();
            try {
                invitationRepository.save(invitation);
            } catch (Exception e) {
                log.error("초대장 정보 저장 중에 에러 발생!!");
            }

            // 3. 문자 전송~






            ResponseSendInvitaionDTO responseSendInvitaionDTO = null;
            try {
                responseSendInvitaionDTO = ResponseSendInvitaionDTO.builder()
                        .userId(requestInvitationDTO.getUserId())
                        .gatherId(requestInvitationDTO.getGatherId())
                        .invitationId(requestInvitationDTO.getInvitationId())
                        .invitationContent(requestInvitationDTO.getInvitationContent())
                        .build();
            } catch (Exception e) {
                log.error("return 만들다 실패");
            }
            return responseSendInvitaionDTO;
        }

        return null;
    }

    @Override
    @Transactional
    public ResponseInvitationDTO acceptInvatation(RequestInvitationDTO requestInvitationDTO) {

        Invitation invitation = invitationRepository.findById(requestInvitationDTO
                .getInvitationId()).orElseThrow(() -> new NoSuchElementException("Invitation not found with id: " + requestInvitationDTO.getInvitationId()));

        invitation.setActiveYn(true);
        ResponseInvitationDTO responseInvitationDTO = null;
        try {
            responseInvitationDTO = modelMapper.map(invitation, ResponseInvitationDTO.class);
        } catch (Exception e) {
            log.error("수락 하다 실패!");
        }
        return responseInvitationDTO;
    }

    @Override
    @Transactional
    public ResponseInvitationDTO refuseInvatation(RequestInvitationDTO requestInvitationDTO) {
        Invitation invitation = invitationRepository.findById(requestInvitationDTO
                .getInvitationId()).orElseThrow(() -> new NoSuchElementException("Invitation not found with id: " + requestInvitationDTO.getInvitationId()));

        invitation.setActiveYn(false);
        ResponseInvitationDTO responseInvitationDTO = null;
        try {
            responseInvitationDTO = modelMapper.map(invitation, ResponseInvitationDTO.class);
        } catch (Exception e) {
            log.error("거절 하다 실패!");
        }
        return responseInvitationDTO;
    }

    @Override
    @Transactional
    public void deleteMember(RequestDeleteMemberDTO requestDeleteMemberDTO) {
        GatherMemberFK gatherMemberFK = GatherMemberFK.builder()
                .gatherId(requestDeleteMemberDTO.getGatherId())
                .memberId(requestDeleteMemberDTO.getUserId())
                .build();
        GatherMember foundGather = gatherMemberRepository.findById(gatherMemberFK)
                .orElseThrow(() -> new NoSuchElementException("해당 모임이 없습니다."));

        if (foundGather.getRole() == GatherRole.LEADER) {
            // tbl_group_member 상태 변경
            gatherMemberFK = GatherMemberFK.builder()
                    .gatherId(requestDeleteMemberDTO.getGatherId())
                    .memberId(requestDeleteMemberDTO.getMemberId())
                    .build();
            // 삭제
            gatherMemberRepository.deleteById(gatherMemberFK);
//            foundGather = gatherMemberRepository.findById(gatherMemberFK)
//                    .orElseThrow(() -> new NoSuchElementException("해당 고객이 없습니다."));
//            foundGather.setRole(GatherRole.NO_MEMBER);

            try {
                GatherMember deleteMember = modelMapper.map(foundGather, GatherMember.class);
            }catch (Exception e){
                log.error("매핑 실패!");
            }
        }
    }
}

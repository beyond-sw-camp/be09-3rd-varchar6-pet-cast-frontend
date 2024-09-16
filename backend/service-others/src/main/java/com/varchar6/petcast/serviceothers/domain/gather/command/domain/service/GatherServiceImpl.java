package com.varchar6.petcast.serviceothers.domain.gather.command.domain.service;

import com.varchar6.petcast.serviceothers.common.exception.CommonException;
import com.varchar6.petcast.serviceothers.common.exception.ErrorCode;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.*;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.service.GatherService;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.entity.Gather;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.GatherRole;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.entity.Invitation;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.repository.GatherMemberRepository;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.repository.GatherRepository;
import com.varchar6.petcast.serviceothers.domain.gather.command.domain.repository.InvitationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service(value = "commandGatherServiceImpl")
public class GatherServiceImpl implements GatherService {

    private final GatherRepository gatherRepository;
    private final GatherMemberRepository gatherMemberRepository;
    private final InvitationRepository invitationRepository;
    private final ModelMapper modelMapper;
    private final com.varchar6.petcast.serviceothers.domain.gather.query.service.GatherService gatherService;

    @Autowired
    public GatherServiceImpl(GatherRepository gatherRepository,
                             GatherMemberRepository gatherMemberRepository,
                             InvitationRepository invitationRepository,
                             ModelMapper modelMapper,
                             com.varchar6.petcast.serviceothers.domain.gather.query.service.GatherService gatherService) {
        this.gatherRepository = gatherRepository;
        this.gatherMemberRepository = gatherMemberRepository;
        this.invitationRepository = invitationRepository;
        this.modelMapper = modelMapper;
        this.gatherService = gatherService;
    }

    @Override
    @Transactional
    public void createGather(RequestCreateGatherDTO requestCreateGatherDTO) {
        String currentDate = getNow();

        // 모임 테이블 insert 과정
        Gather gather = Gather.builder()
                .name(requestCreateGatherDTO.getName())
                .content(requestCreateGatherDTO.getContent())
                .number(requestCreateGatherDTO.getNumber())
                .url(requestCreateGatherDTO.getUrl())
                .createdAt(currentDate)
                .updatedAt(currentDate)
                .active(true)
                .build();

        Gather newGather = gatherRepository.save(gather);

        // 새로 생성한 모임에 리더 지정
        GatherMember newGatherMember = GatherMember.builder()
                .role(GatherRole.LEADER)
                .gatherId(newGather.getId())
                .memberId(requestCreateGatherDTO.getUserId())
                .build();

        GatherMember gatherMember = gatherMemberRepository.save(newGatherMember);
    }

    @Override
    @Transactional
    public ResponseUpdateGatherInfoDTO updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO) {
        String currentDate = getNow();

        // 멤버 역할 꺼내오기
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestUpdateGatherDTO.getGatherId());
        params.put("member_id", requestUpdateGatherDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(params);
        if ("".equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_FOUND_MEMBER_ROLE);
        }
        log.warn(memberRole);

        // 해당 회원이 리더인지 확인
        if (!memberRole.equals(GatherRole.LEADER.toString())) {
            throw new CommonException(ErrorCode.NOT_LEADER);
        }

        // 회원이 속한 모임 찾기
        Optional<Gather> gather = gatherRepository.findById(requestUpdateGatherDTO.getGatherId());
        if(gather.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER);
        }

        // 모임 정보 수정하기
        Gather updateGather = gather.get();
        updateGather.setName(requestUpdateGatherDTO.getName());
        updateGather.setContent(requestUpdateGatherDTO.getContent());
        updateGather.setNumber(requestUpdateGatherDTO.getNumber());
        updateGather.setUrl(requestUpdateGatherDTO.getUrl());
        updateGather.setUpdatedAt(currentDate);
        gatherRepository.save(updateGather);

        return ResponseUpdateGatherInfoDTO.builder()
                .name(updateGather.getName())
                .content(updateGather.getContent())
                .number(updateGather.getNumber())
                .url(updateGather.getUrl())
                .active(updateGather.isActive())
                .build();
    }

    @Override
    @Transactional
    public ResponseDeactiveGatherDTO deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO) {
        String currentDate = getNow();

        /* Leader인지 확인 */
        // 멤버 역할 꺼내오기
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestDeactiveGatherDTO.getGatherId());
        params.put("member_id", requestDeactiveGatherDTO.getUserId());

        String memberRole = (String) gatherService.findMemberRoleById(params);
        if ("".equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_FOUND_MEMBER_ROLE);
        }

        // 해당 회원이 리더인지 확인
        if (!GatherRole.LEADER.toString().equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_LEADER);
        }

        // 회원이 속한 모임 찾기
        Optional<Gather> gather = gatherRepository.findById(requestDeactiveGatherDTO.getGatherId());
        if(gather.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER);
        }

        // 비활성화로 수정
        Gather updateGather = gather.get();
        updateGather.setActive(false);
        updateGather.setUpdatedAt(currentDate);
        gatherRepository.save(updateGather);

        return ResponseDeactiveGatherDTO.builder()
                .userId(requestDeactiveGatherDTO.getUserId())
                .gatherId(updateGather.getId())
                .build();
    }

    @Override
    @Transactional
    public ResponseSendInvitaionDTO sendInvitation(RequestSendInvitationDTO requestInvitationDTO) {

        // 1. 해당 모임의 모임장인지 확인
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestInvitationDTO.getGatherId());
        params.put("member_id", requestInvitationDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(params);
        if("".equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_FOUND_MEMBER_ROLE);
        }

        // 멤버 역할이 리더인지 확인
        if (!GatherRole.LEADER.toString().equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_LEADER);
        }

        // 초대장 만들고 DB에 저장
        String currentDate = getNow();
        Invitation invitation = Invitation.builder()
                .active(true)
                .createdAt(currentDate)
                .userId(requestInvitationDTO.getUserId())
                .gatherId(requestInvitationDTO.getGatherId())
                .build();
        invitationRepository.save(invitation);

        /*
        * 초대장 전송~~~~~~?
        * */

        return ResponseSendInvitaionDTO.builder()
                .userId(requestInvitationDTO.getUserId())
                .gatherId(requestInvitationDTO.getGatherId())
                .build();
    }

    @Override
    @Transactional
    public ResponseInvitationDTO acceptInvatation(RequestInvitationDTO requestInvitationDTO) {

        // 초대장 찾기
        Optional<Invitation> invitation = invitationRepository.findById(requestInvitationDTO.getInvitationId());
        if(invitation.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_INVITATION);
        }

        // 초대장 수락
        Invitation acceptInvatation = invitation.get();
        acceptInvatation.setActive(true);
        invitationRepository.save(acceptInvatation);

        return ResponseInvitationDTO.builder()
                .userId(requestInvitationDTO.getUserId())
                .invitationId(acceptInvatation.getId())
                .build();
    }

    @Override
    @Transactional
    public ResponseInvitationDTO refuseInvatation(RequestInvitationDTO requestInvitationDTO) {
        // 초대장 찾기
        Optional<Invitation> invitation = invitationRepository.findById(requestInvitationDTO.getInvitationId());
        if(invitation.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_INVITATION);
        }

        // 초대장 거절하기
        Invitation refuseInvatation = invitation.get();
        refuseInvatation.setActive(false);
        invitationRepository.save(refuseInvatation);

        return ResponseInvitationDTO.builder()
                .userId(requestInvitationDTO.getUserId())
                .invitationId(refuseInvatation.getId())
                .build();
    }

    @Override
    @Transactional
    public void deleteMember(RequestDeleteMemberDTO requestDeleteMemberDTO) {

        // LEADER인지 확인
        Map<String, Object> paramsR = new HashMap<>();
        paramsR.put("selectValue", "role");
        paramsR.put("gather_id", requestDeleteMemberDTO.getGatherId());
        paramsR.put("member_id", requestDeleteMemberDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(paramsR);
        if("".equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_FOUND_MEMBER_ROLE);
        }

        // 리더인지 확인
        if (!GatherRole.LEADER.toString().equals(memberRole)) {
            throw new CommonException(ErrorCode.NOT_LEADER);
        }

        // 모임 구성원 아이디 찾기(pk)
        Map<String, Object> paramsI = new HashMap<>();
        paramsI.put("selectValue", "id");
        paramsI.put("gather_id", requestDeleteMemberDTO.getGatherId());
        paramsI.put("member_id", requestDeleteMemberDTO.getMemberId());
        int id = (Integer) gatherService.findMemberRoleById(paramsI);
        if(id == 0){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER_MEMBER);
        }

        // 삭제
        gatherMemberRepository.deleteById(id);
    }

    private static String getNow() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return simpleDateFormat.format(now);
    }
}

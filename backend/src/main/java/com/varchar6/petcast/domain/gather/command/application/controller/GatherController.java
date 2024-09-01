package com.varchar6.petcast.domain.gather.command.application.controller;

import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.domain.gather.command.application.dto.response.*;
import com.varchar6.petcast.domain.gather.command.application.service.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value="commandGatherController")
@RequestMapping("/api/v1/gather/command")
@Slf4j
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @PostMapping("/createGather")
    public ResponseEntity<ResponseCreateGatherDTO> createGather(@RequestBody RequestCreateGatherDTO requestCreateGatherDTO){

        gatherService.createGather(requestCreateGatherDTO);

        ResponseCreateGatherDTO responseCreateGatherDTO =
                ResponseCreateGatherDTO.builder().message("모임 생성 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseCreateGatherDTO);
    }

    @PutMapping("/updateGatherInfo")
    public ResponseEntity<ResponseUpdateGatherInfoDTO> updateGatherInfo(@RequestBody RequestUpdateGatherInfoDTO requestUpdateGatherDTO){

        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = gatherService.updateGatherInfo(requestUpdateGatherDTO);

        if(responseUpdateGatherInfoDTO == null){
            responseUpdateGatherInfoDTO.setMessage("모임 정보 수정 실패!");
        }else{
            responseUpdateGatherInfoDTO.setMessage("모임 정보 수정 성공!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseUpdateGatherInfoDTO);
    }

    @PutMapping("/deactiveGather")
    public ResponseEntity<ResponseDeactiveGatherDTO> deactiveGather(@RequestBody RequestDeactiveGatherDTO requestDeactiveGatherDTO){

        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = gatherService.deactiveGather(requestDeactiveGatherDTO);

        if(responseDeactiveGatherDTO == null){
            responseDeactiveGatherDTO.setMessage("모임 비활성화 실패!");
        }else{
            responseDeactiveGatherDTO.setMessage("모임 비활성화 성공!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDeactiveGatherDTO);
    }

    @PostMapping("/sendInvitation")
    public ResponseEntity<ResponseSendInvitaionDTO> sendInvitation(@RequestBody RequestSendInvitationDTO requestInvitationDTO) {

        ResponseSendInvitaionDTO responseSendInvitaionDTO = gatherService.sendInvitation(requestInvitationDTO);

        if(responseSendInvitaionDTO == null){
            responseSendInvitaionDTO.setMessage("초대장 전송 실패~");
        }else{
            responseSendInvitaionDTO.setMessage("초대장 전송 성공~");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseSendInvitaionDTO);
    }

    @PutMapping("/acceptInvitation")
    public ResponseEntity<ResponseInvitationDTO> acceptInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO){

        ResponseInvitationDTO responseInvitationDTO = gatherService.acceptInvatation(requestInvitationDTO);

        if(responseInvitationDTO == null){
            responseInvitationDTO.setMessage("수락 실패!");
        }else{
            responseInvitationDTO.setMessage("수락 성공!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseInvitationDTO);
    }

    @PutMapping("/refuseInvitation")
    public ResponseEntity<ResponseInvitationDTO> refuseInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO){
        ResponseInvitationDTO responseInvitationDTO = gatherService.refuseInvatation(requestInvitationDTO);

        if(responseInvitationDTO == null){
            responseInvitationDTO.setMessage("거절 실패!");
        }else{
            responseInvitationDTO.setMessage("거절 성공!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseInvitationDTO);
    }



}

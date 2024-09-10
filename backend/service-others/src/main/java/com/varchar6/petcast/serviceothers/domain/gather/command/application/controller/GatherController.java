package com.varchar6.petcast.serviceothers.domain.gather.command.application.controller;

import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestCreateGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestDeactiveGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestDeleteMemberDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestSendInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestUpdateGatherInfoDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseDeactiveGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseSendInvitaionDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseUpdateGatherInfoDTO;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.service.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandGatherController")
@RequestMapping("/api/v1/gather")
@Slf4j
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createGather(@RequestHeader(value = "X-Member-Id", required = false) String id,
                                                         @RequestBody RequestCreateGatherDTO requestCreateGatherDTO) {
        String message = "모임 생성 성공!";
        requestCreateGatherDTO.setUserId(Integer.parseInt(id));
        try {
            gatherService.createGather(requestCreateGatherDTO);
        } catch (Exception e) {
            message = "모임 생성 실패!";
            throw new RuntimeException(message);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.CREATED.value())
                        .message(message)
                        .build()
        );
    }

    @PutMapping("")
    public ResponseEntity<ResponseMessage> updateGatherInfo(@RequestBody RequestUpdateGatherInfoDTO requestUpdateGatherDTO
            , @RequestHeader("X-Member-Id") String id
    ) {
        String message = "모임 수정 실패!";
        requestUpdateGatherDTO.setUserId(Integer.parseInt(id));
        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO;
        try {
            responseUpdateGatherInfoDTO = gatherService.updateGatherInfo(requestUpdateGatherDTO);
        } catch (Exception e) {
            message = "모임 수정 실패!";
            throw new RuntimeException(message);
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(responseUpdateGatherInfoDTO)
                        .build());
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseMessage> deactiveGather(@RequestBody RequestDeactiveGatherDTO requestDeactiveGatherDTO
            , @RequestHeader("X-Member-Id") String id
    ) {
        String message = "모임 비활성화 성공!";
        requestDeactiveGatherDTO.setUserId(Integer.parseInt(id));

        ResponseDeactiveGatherDTO responseDeactiveGatherDTO;
        try {
            responseDeactiveGatherDTO = gatherService.deactiveGather(requestDeactiveGatherDTO);
        } catch (Exception e) {
            message = "모임 비활성화 실패!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(responseDeactiveGatherDTO)
                        .build());
    }

    @PostMapping("/invitation")
    public ResponseEntity<ResponseMessage> sendInvitation(@RequestBody RequestSendInvitationDTO requestSendInvitationDTO,
                                                          @RequestHeader("X-Member-Id") String id) {

        String message = "초대장 전송 성공!";
        requestSendInvitationDTO.setUserId(Integer.parseInt(id));

        ResponseSendInvitaionDTO responseSendInvitaionDTO;
        try {
            responseSendInvitaionDTO = gatherService.sendInvitation(requestSendInvitationDTO);
        } catch (Exception e) {
            message = "초대장 전송 실패~";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(responseSendInvitaionDTO)
                        .build()
        );
    }

    @PutMapping("/invitation/accept")
    public ResponseEntity<ResponseMessage> acceptInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestHeader("X-Member-Id") String id) {
        String message = "수락 성공!";
        requestInvitationDTO.setUserId(Integer.parseInt(id));
        ResponseInvitationDTO responseInvitationDTO;
        try {
            responseInvitationDTO = gatherService.acceptInvatation(requestInvitationDTO);
        } catch (Exception e) {
            message = "수락 실패!";
            throw new RuntimeException(message, e);
        }
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(responseInvitationDTO)
                        .build()
        );
    }

    @PutMapping("/invitation/refuse")
    public ResponseEntity<ResponseMessage> refuseInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestHeader("X-Member-Id") String id) {

        String message = "거절 성공!";
        requestInvitationDTO.setUserId(Integer.parseInt(id));
        ResponseInvitationDTO responseInvitationDTO;
        try {
            responseInvitationDTO = gatherService.refuseInvatation(requestInvitationDTO);
        } catch (Exception e) {
            message = "거절 실패!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(responseInvitationDTO)
                        .build()
        );
    }

    @DeleteMapping("/member")
    public ResponseEntity<ResponseMessage> deleteMember(@RequestBody RequestDeleteMemberDTO requestDeleteMemberDTO,
                                                        @RequestHeader("X-Member-Id") String id) {
        String message = "삭제 성공!";
        requestDeleteMemberDTO.setUserId(Integer.parseInt(id));
        try {
            gatherService.deleteMember(requestDeleteMemberDTO);
        } catch (Exception e) {
            message = "삭제 실패!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .message(message)
                        .build()
        );
    }
}

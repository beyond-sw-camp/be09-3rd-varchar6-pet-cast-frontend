package com.varchar6.petcast.serviceothers.domain.gather.query.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.serviceothers.domain.gather.query.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "queryGatherController")
@RequestMapping("/api/v1/gather")
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @GetMapping("/grouplist")
    public ResponseEntity<ResponseMessage> findAllGather(@RequestHeader("X-Member-Id") String id) {
        String message = "모임 목록 조회 성공!";
        List<String> gathers = null;
        try {
            gathers = gatherService.findAllGather(Integer.parseInt(id));
        } catch (Exception e) {
            message = "모임 목록 조회 실패!";
            throw new RuntimeException(message, e);
        }
        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message(message)
                .result(gathers)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<ResponseMessage> findDetailGather(@PathVariable("gatherId") int gatherId) {
        String message = "모임 상세 조회 성공!";
        GatherDetailDTO gatherDetail = null;
        try {
            gatherDetail = gatherService.findDetailGather(gatherId);
        } catch (Exception e) {
            message = "모임 상세 조회 실패!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(gatherDetail)
                        .build()
        );
    }

    @GetMapping("/invitation/{invitationId}")
    public ResponseEntity<ResponseMessage> findInvitationPageById(@PathVariable("invitationId") int invitationId,
                                                                  @RequestHeader("X-Member-Id") String id) {
        String message = "모임 접근 가능!";
        Boolean isAccessTrueGather = null;
        try {
            isAccessTrueGather = gatherService.isAccessTrueGather(invitationId, Integer.parseInt(id));
        } catch (Exception e) {
            message = "모임 접근 불가!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(isAccessTrueGather)
                        .build()
        );
    }

    @GetMapping("groupmemberlist/{gatherId}")
    public ResponseEntity<ResponseMessage> findGroupMemberById(@PathVariable("gatherId") int gatherId) {
        String message = "모임원 목록 조회 성공!";
        List<String> groupMembers = null;
        try {
            groupMembers = gatherService.findGroupMemberById(gatherId);
        } catch (Exception e) {
            message = "모임원 목록 조회 실패!";
            throw new RuntimeException(message, e);
        }

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(groupMembers)
                        .build()
        );
    }

}

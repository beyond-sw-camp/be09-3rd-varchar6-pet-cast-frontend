package com.varchar6.petcast.domain.gather.command.application.controller;

import com.varchar6.petcast.domain.gather.command.application.dto.CreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestCreateGatherVO;
import com.varchar6.petcast.domain.gather.command.application.dto.response.ResponseCreateGatherVO;
import com.varchar6.petcast.domain.gather.command.application.service.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseCreateGatherVO> createGather(@RequestBody RequestCreateGatherVO requestCreateGatherVO){
        CreateGatherDTO createGatherDTO = CreateGatherDTO.builder()
                .name(requestCreateGatherVO.getName())
                .content(requestCreateGatherVO.getContent())
                .number(requestCreateGatherVO.getNumber())
                .url(requestCreateGatherVO.getUrl())
                .updatedAt(requestCreateGatherVO.getUpdatedAt())
                .createdAt(requestCreateGatherVO.getCreatedAt())
                .active(requestCreateGatherVO.isActive())
                .invitationId(requestCreateGatherVO.getInvitationId())
                .invitationContent(requestCreateGatherVO.getInvitationContent())
                .userId(requestCreateGatherVO.getUserId())
                .build();

//        ResponseCreateGatherVO responseCreateGatherVO =
        gatherService.createGather(createGatherDTO);

        ResponseCreateGatherVO responseCreateGatherVO = ResponseCreateGatherVO.builder()
                .message("모임 생성 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseCreateGatherVO);
    }

}

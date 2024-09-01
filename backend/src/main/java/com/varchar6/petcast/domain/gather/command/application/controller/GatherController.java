package com.varchar6.petcast.domain.gather.command.application.controller;

import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestCreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestDeactiveGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestUpdateGatherInfoDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.response.ResponseDeactiveGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.response.ResponseCreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.response.ResponseUpdateGatherInfoDTO;
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

        ResponseCreateGatherDTO responseCreateGatherDTO = ResponseCreateGatherDTO.builder()
                .message("모임 생성 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseCreateGatherDTO);
    }

    @PutMapping("/updateGatherInfo")
    public ResponseEntity<ResponseUpdateGatherInfoDTO> updateGatherInfo(@RequestBody RequestUpdateGatherInfoDTO requestUpdateGatherDTO){

        gatherService.updateGatherInfo(requestUpdateGatherDTO);

        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = ResponseUpdateGatherInfoDTO.builder()
                .message("모임 수정 성공!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseUpdateGatherInfoDTO);
    }

    @PutMapping("/deactiveGather")
    public ResponseEntity<ResponseDeactiveGatherDTO> deactiveGather(@RequestBody RequestDeactiveGatherDTO requestDeactiveGatherDTO){

        gatherService.deactiveGather(requestDeactiveGatherDTO);

        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = ResponseDeactiveGatherDTO.builder()
                .message("모임 비활성화 시작~~")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDeactiveGatherDTO);
    }
}

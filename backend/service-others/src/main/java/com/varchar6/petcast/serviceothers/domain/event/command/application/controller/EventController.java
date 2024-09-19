package com.varchar6.petcast.serviceothers.domain.event.command.application.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.event.command.application.dto.request.EventSetStatusRequestDTO;
import com.varchar6.petcast.serviceothers.domain.event.command.application.dto.request.EventUpdateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.event.command.application.dto.response.EventResponseDTO;
import com.varchar6.petcast.serviceothers.domain.event.command.application.service.EventService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "commandEventController")
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createEvent(@RequestBody EventCreateRequestDTO eventCreateRequestDTO){

        eventService.insertEvent(eventCreateRequestDTO);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("이벤트 생성 성공")
                    .result(null)
                    .build()
            );
    }

    @PutMapping("")
    private ResponseEntity<ResponseMessage> updateEvent(@RequestBody EventUpdateRequestDTO eventUpdateRequestDTO){

        EventResponseDTO eventResponseDTO = eventService.updateEvent(eventUpdateRequestDTO);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("이벤트 수정 성공")
                    .result(eventResponseDTO)
                    .build()
            );
    }

    @PutMapping("/status")
    private ResponseEntity<ResponseMessage> setEventStatus(@RequestBody EventSetStatusRequestDTO eventSetStatusRequestDTO){

        EventResponseDTO eventResponseDTO = eventService.setEventStatus(eventSetStatusRequestDTO);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.CREATED.value())
                    .message("이벤트 수정 성공")
                    .result(eventResponseDTO)
                    .build()
            );
    }

    @DeleteMapping("/{eventId}")
    private ResponseEntity<ResponseMessage> deleteEvent(@PathVariable Integer eventId, @RequestBody Map<String, Integer> request){
        Integer companyId = request.get("companyId");

        int result = eventService.deleteEvent(companyId, eventId);

        if (result == 1){
            return ResponseEntity
                .ok()
                .body(
                    ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("이벤트 삭제 성공")
                        .result(null)
                        .build()
                );
        }
        else{
            return ResponseEntity
                .ok()
                .body(
                    ResponseMessage.builder()
                        .httpStatus(HttpStatus.NO_CONTENT.value())
                        .message("이벤트 삭제 실패. 회사 아이디가 맞지 않습니다")
                        .result(null)
                        .build()
                );
        }

    }


}

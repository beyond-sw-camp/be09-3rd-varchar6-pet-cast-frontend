package com.varchar6.petcast.domain.event.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.query.dto.EventDTO;
import com.varchar6.petcast.domain.event.query.service.EventService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "queryEventController")
@RequestMapping("/api/v1/event")
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/company/{companyId}")
    private ResponseEntity<ResponseMessage> findEventByCompany(@PathVariable Integer companyId,
        @RequestParam Integer lastEventId){

        List<EventDTO> responseEvents = eventService.findEventByCompany(companyId, lastEventId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
            , responseEvents));
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<ResponseMessage> findEventByCustomer(@PathVariable Integer userId,
        @RequestParam Integer lastEventId){

        List<EventDTO> responseEvents = eventService.findEventByCustomer(userId, lastEventId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
            , responseEvents));
    }

    @GetMapping("/{eventId}")
    private ResponseEntity<ResponseMessage> findEvent(@PathVariable Integer eventId){

        EventDTO responseEvent = eventService.findEvent(eventId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
            , responseEvent));
    }


}

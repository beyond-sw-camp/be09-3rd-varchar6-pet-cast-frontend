package com.varchar6.petcast.domain.gather.query.controller;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "queryGatherController")
@RequestMapping("/api/v1/gather/query")
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @GetMapping("/grouplist/{userId}")
    public ResponseEntity<List<GatherDTO>> findAllGather(@PathVariable("userId") int userId){
        List<GatherDTO> gathers = gatherService.findAllGather(userId);
        return ResponseEntity.ok(gathers);
    }

    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<GatherDetailDTO> findDetailGather(@PathVariable("gatherId") int gatherId){
        GatherDetailDTO gatherDetail = gatherService.findDetailGather(gatherId);
        return ResponseEntity.ok(gatherDetail);
    }

    @GetMapping("/invitation/{invitationId}/{userId}")
    public ResponseEntity<Boolean> findInvitationPageById(@PathVariable("invitationId") int invitationId,
                                                          @PathVariable("userId") int userId){
        Boolean isAccessTrueGather = gatherService.isAccessTrueGather(invitationId, userId);
        return ResponseEntity.ok(isAccessTrueGather);
    }

    @GetMapping("groupmemberlist/{gatherId}")
    public ResponseEntity<List<String>> findGroupMemberById(@PathVariable("gatherId") int gatherId){
        List<String> groupMembers = gatherService.findGroupMemberById(gatherId);
        return ResponseEntity.ok(groupMembers);
    }

}

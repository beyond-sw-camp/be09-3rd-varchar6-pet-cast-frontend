package com.varchar6.petcast.domain.gather.query.controller;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public ResponseEntity<List<GatherDTO>> findAllGather(@RequestParam("userId") int userId){
        List<GatherDTO> gathers = gatherService.findAllGather(userId);
        return ResponseEntity.ok(gathers);
    }


}

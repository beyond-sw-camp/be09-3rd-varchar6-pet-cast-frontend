package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.CreateGatherDTO;

public interface GatherService {
    void createGather(CreateGatherDTO createGatherDTO);
}

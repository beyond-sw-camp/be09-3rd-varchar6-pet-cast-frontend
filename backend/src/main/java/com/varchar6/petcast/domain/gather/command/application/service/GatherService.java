package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestCreateGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestDeactiveGatherDTO;
import com.varchar6.petcast.domain.gather.command.application.dto.request.RequestUpdateGatherInfoDTO;

public interface GatherService {
    void createGather(RequestCreateGatherDTO createGatherDTO);

    void updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO);

    void deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO);
}

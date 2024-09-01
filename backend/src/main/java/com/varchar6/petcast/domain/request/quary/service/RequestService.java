package com.varchar6.petcast.domain.request.quary.service;


import com.varchar6.petcast.domain.request.command.entity.Request;
import com.varchar6.petcast.domain.request.quary.dto.RequestDTO;

import java.util.List;

public interface RequestService {


    List<RequestDTO> findAllRequests();

    RequestDTO findRequestById(int id);

    Request createRequest(Request request);

    Request updateRequest(int id, Request request);

    void deleteRequest(int id);
}

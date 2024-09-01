package com.varchar6.petcast.domain.request.quary.service;

import com.varchar6.petcast.domain.request.command.entity.Request;
import com.varchar6.petcast.domain.request.quary.dto.RequestDTO;
import com.varchar6.petcast.domain.request.quary.dto.RequestResponseDTO;
import com.varchar6.petcast.domain.request.quary.repository.RequestMapper;
import com.varchar6.petcast.domain.request.quary.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static RequestServiceImpl.FORMATTER;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public List<RequestDTO> findAllRequests() {
        return requestMapper.findAllRequests();
    }

    @Override
    public RequestDTO findRequestById(int id) {
        return requestMapper.findRequestById(id);
    }

    // JPA를 사용한 생성 기능
    @Transactional
    public RequestDTO createRequest(RequestDTO requestDTO) {
        Request request = requestRepository.save(
                requestDTOToEntity(requestDTO)
        );

        return entityToResponseDTO(request);
    }

        // 요청서 저장
        Request savedRequest = requestRepository.save(request);
        System.out.println("요청서가 저장되었습니다: " + savedRequest);

        return savedRequest;
    }

    // JPA를 사용한 수정 기능
    @Transactional
    public RequestResponseDTO updateRequest(RequestUpdateRequestDTO requestUpdateRequestDTO) {
        Request foundRequest = requestRepository.findById(requestUpdateRequestDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("요청서를 찾을 수 없습니다. ID: " + requestUpdateRequestDTO.getId()));

        foundRequest.setContent(requestUpdateRequestDTO.getContent());
        foundRequest.setCost(requestUpdateRequestDTO.getCost());
        foundRequest.setLocation(requestUpdateRequestDTO.getLocation());
        foundRequest.setTime(requestUpdateRequestDTO.getTime());
        foundRequest.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        return entityToResponseDTO(foundRequest);
    }eturn updatedRequest;
    }

@Transactional
public void deleteRequest(Long requestId) {
    requestRepository.deleteById(requestId);
}

private Request requestDTOToEntity(RequestCreateRequestDTO requestCreateRequestDTO) {
    return Request.builder()
            .content(requestCreateRequestDTO.getContent())
            .cost(requestCreateRequestDTO.getCost())
            .location(requestCreateRequestDTO.getLocation())
            .time(
                    LocalDateTime.now()
                            .format(FORMATTER))
            .createdAt(LocalDateTime.now().format(FORMATTER))
            .updatedAt(LocalDateTime.now().format(FORMATTER))
            .activeYn(true)
            .build();
}

private RequestResponseDTO entityToResponseDTO(Request request) {
    return RequestResponseDTO.builder()
            .id(request.getId())
            .content(request.getContent())
            .cost(request.getCost())
            .location(request.getLocation())
            .time(
                    LocalDateTime.now()
                            .format(FORMATTER))
            .createdAt(request.getCreatedAt())
            .updatedAt(request.getUpdatedAt())
            .activeYn(request.getActiveYn())
            .build();
}
}

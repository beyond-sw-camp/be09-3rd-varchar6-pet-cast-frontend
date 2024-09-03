package com.varchar6.petcast.domain.request.service;

import com.varchar6.petcast.domain.request.aggregate.Request;
import com.varchar6.petcast.domain.request.dto.RequestDTO;
import com.varchar6.petcast.domain.request.dto.RequestRequestDTO;
import com.varchar6.petcast.domain.request.dto.RequestResponseDTO;
import com.varchar6.petcast.domain.request.repository.RequestMapper;
import com.varchar6.petcast.domain.request.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
                              RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    // 고객 요청서 목록 조회
    @Override
    public List<RequestResponseDTO> findAllRequestsByMemberId(int memberId) {
        List<Request> requests = requestMapper.findAllRequestsByMemberId(memberId);
        return requests.stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    // 업체 요청서 목록 조회
    @Override
    public List<RequestResponseDTO> findAllRequestsByCompanyId(int companyId) {
        List<Request> requests = requestMapper.findAllRequestsByCompanyId(companyId);
        return requests.stream()
                .map(this::entityToResponseDTO)
                .toList();
    }

    // 요청서 상세 조회
    @Override
    public RequestDTO findRequestById(int requestId) {
        if (requestId == null) {
            throw new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다.");
        }
        return requestMapper.findRequestById(requestId);
    }


    // 요청서 작성
    @Transactional
    public RequestResponseDTO createRequest(RequestRequestDTO requestRequestDTO) {
        requestRequestDTO.setContent(Request.getContent);
    }
//        Request request = requestDTOToEntity(requestRequestDTO);
//        request = requestRepository.save(request);
        return entityToResponseDTO(request);
    }

    // 요청서 삭제
    @Transactional
    public void deleteRequest(int requestId) {
        if (!requestRepository.existsById(requestId)) {
            throw new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다.");
        }
        requestRepository.deleteById(requestId);
    }

    // 요청서 수락
    @Transactional
    public RequestResponseDTO acceptRequest(int requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.accept();  // 상태 변경
        request = requestRepository.save(request);  // 상태 업데이트 저장
        return entityToResponseDTO(request);
    }

    // 요청서 거절
    @Transactional
    public RequestResponseDTO rejectRequest(int requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.reject();  // 상태 변경
        request = requestRepository.save(request);  // 상태 업데이트 저장
        return entityToResponseDTO(request);
    }

    private Request requestDTOToEntity(RequestRequestDTO requestRequestDTO) {
        return Request.builder()
                .content(requestRequestDTO.getContent())
                .cost(requestRequestDTO.getCost())
                .location(requestRequestDTO.getLocation())
                .time(requestRequestDTO.getTime())
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .build();
    }

    private RequestResponseDTO entityToResponseDTO(Request request) {
        return RequestResponseDTO.builder()
                .id(request.getId())
                .content(request.getContent())
                .cost(request.getCost())
                .location(request.getLocation())
                .time(request.getTime())
                .createdAt(request.getCreated_at())
                .updatedAt(request.getUpdated_at())
                .active(request.isActive())
                .build();


}

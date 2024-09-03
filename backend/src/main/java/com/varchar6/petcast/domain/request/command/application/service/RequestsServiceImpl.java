package com.varchar6.petcast.domain.request.command.application.service;

import com.varchar6.petcast.domain.request.command.application.dto.RequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.application.dto.RequestsResponseDTO;
import com.varchar6.petcast.domain.request.command.domain.aggregate.Requests;
import com.varchar6.petcast.domain.request.command.domain.repository.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RequestsServiceImpl implements RequestsService{
    private final RequestsRepository requestsRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestsServiceImpl(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    // 요청서 작성
    @Transactional
    public RequestsResponseDTO createRequest(RequestsRequestDTO requestsRequestDTO) {

        Requests requests = Requests.builder()
            .content(requestsRequestDTO.getContent())
            .hopeCost(requestsRequestDTO.getHopeCost())
            .hopeLocation(requestsRequestDTO.getHopeLocation())
            .hopeTime(requestsRequestDTO.getHopeTime())
            .createdAt(LocalDateTime.now().format(FORMATTER))
            .updatedAt(LocalDateTime.now().format(FORMATTER))
            .active(true)
            .companyId(requestsRequestDTO.getCompanyId())
            .memberId(requestsRequestDTO.getMemberId())
            .build();


        RequestsResponseDTO.builder()
                .id(requests.getId())
                .content(requests.getContent())
                .hopeCost(requests.getHopeCost())
                .hopeLocation(requests.getHopeLocation())
                .hopeTime(requests.getHopeTime())
                .build();

        return entityToResponseDTO(requests);
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

    private Request requestDTOToEntity(RequestsRequestDTO requestsRequestDTO) {
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

}

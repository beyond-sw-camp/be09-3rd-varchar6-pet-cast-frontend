package com.varchar6.petcast.domain.request.command.application.service;

import com.varchar6.petcast.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.domain.aggregate.RequestsStatus;
import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Requests;
import com.varchar6.petcast.domain.request.command.domain.repository.EventsRepository;
import com.varchar6.petcast.domain.request.command.domain.repository.RequestsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service(value = "comandRequestServiceImpl")
@Slf4j
public class RequestsServiceImpl implements RequestsService {
    private final RequestsRepository requestsRepository;
    private final EventsRepository eventsRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestsServiceImpl(RequestsRepository requestsRepository,
                               EventsRepository eventsRepository) {
        this.requestsRepository = requestsRepository;
        this.eventsRepository = eventsRepository;
    }

    // 요청서 작성
    @Transactional
    public void createRequest(CreateRequestsRequestDTO createRequestsRequestDTO, int memberId) {
        Requests requests = Requests.builder()
                .content(createRequestsRequestDTO.getContent())
                .hopeCost(createRequestsRequestDTO.getHopeCost())
                .hopeLocation(createRequestsRequestDTO.getHopeLocation())
                .hopeTime(createRequestsRequestDTO.getHopeTime())
                .status(RequestsStatus.SENT)
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .createdAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .companyId(createRequestsRequestDTO.getCompanyId())
                .memberId(memberId)
                .build();

        try {
            requestsRepository.save(requests);
        } catch (Exception e) {
            log.warn("[Service] Repository에 넣다가 실패!");
        }
    }

    // 요청서 삭제
    @Transactional
    public void deleteRequest(int requestId, int memberId) {
        Requests findRequests = requestsRepository.findById(requestId).orElseThrow(
                () -> (new NoSuchElementException("해당 요청서가 없습니다."))
        );
        findRequests.setActive(false);

        requestsRepository.save(findRequests);
    }

    // 요청서 수락
    @Transactional
    public void acceptRequest(int requestId) {
        Requests request = requestsRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.setStatus(RequestsStatus.CONFIRMED);    // 상태 변경
        try {
            requestsRepository.save(request);  // 상태 업데이트 저장
        }catch (Exception e) {
            log.warn("[Service] 요청서 수락 실패!");
        }

        Event newEvent = Event.builder()
                .title(request.getMemberId()+"님의 "+request.getCompanyId()+"기업 요청서")
                .content(request.getContent())
                .companyId(request.getCompanyId())
                .memberId(request.getMemberId())
                .build();
        try {
            eventsRepository.save(newEvent);
        }catch (Exception e){
            log.warn("이벤트 생성 실패!");
        }
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

    private Request requestDTOToEntity(CreateRequestsRequestDTO requestsRequestDTO) {
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

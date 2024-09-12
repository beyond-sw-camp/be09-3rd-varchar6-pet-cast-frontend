package com.varchar6.petcast.serviceothers.domain.request.command.application.service;

import com.varchar6.petcast.serviceothers.domain.category.command.domain.repository.CategoryRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.EventsStatus;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.RequestsStatus;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.RequestCategory;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.Requests;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.EventsRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestCategoryRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestsRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service("commandRequestsServiceImpl")
@Slf4j
public class RequestsServiceImpl implements RequestsService {
    private final RequestsRepository requestsRepository;
    private final EventsRepository eventsRepository;
    private final RequestCategoryRepository requestCategoryRepository;
    private final CategoryRepository categoryRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestsServiceImpl(RequestsRepository requestsRepository,
                               EventsRepository eventsRepository,
                               RequestCategoryRepository requestCategoryRepository,
                               CategoryRepository categoryRepository) {
        this.requestsRepository = requestsRepository;
        this.eventsRepository = eventsRepository;
        this.requestCategoryRepository = requestCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    // 요청서 작성
    @Override
    @Transactional
    public int createRequest(CreateRequestsRequestDTO createRequestsRequestDTO) {
        Requests requests = Requests.builder()
                .content(createRequestsRequestDTO.getContent())
                .hopeCost(createRequestsRequestDTO.getHopeCost())
                .hopeLocation(createRequestsRequestDTO.getHopeLocation())
                .hopeTime(createRequestsRequestDTO.getHopeTime())
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .createdAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .companyId(createRequestsRequestDTO.getCompanyId())
                .build();

        Requests savedRequest = requestsRepository.save(requests);

        List<Integer> categoryIds = createRequestsRequestDTO.getCategoryId();

        List<Integer> invalidCategoryIds = categoryIds.stream()
                .filter(categoryId -> !categoryRepository.existsById(categoryId))
                .collect(Collectors.toList());

        if (!invalidCategoryIds.isEmpty()) {
            throw new IllegalArgumentException("해당 카테고리가 없습니다!");
        }
        
        categoryIds.forEach(categoryId -> {
            RequestCategory requestCategory = new RequestCategory();
            requestCategory.setRequestId(savedRequest.getId());
            requestCategory.setCategoryId(categoryId);
            requestCategoryRepository.save(requestCategory);
        });

        log.info(savedRequest.getId() + "번 요청서가 성공적으로 저장되었습니다.");
        return 0;
    }


    // 요청서 삭제
    @Override
    @Transactional
    public void deleteRequest(int requestId, int memberId) {
        Requests findRequests = requestsRepository.findById(requestId).orElseThrow(
                () -> (new NoSuchElementException("해당 요청서가 없습니다."))
        );
        findRequests.setActive(false);
        try {
            requestsRepository.save(findRequests);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 비활성화 DB에 업데이트 실패!", e);
        }
    }

    // 요청서 수락
    @Override
    @Transactional
    public void acceptRequest(int requestId) {
        Requests request = requestsRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.setStatus(RequestsStatus.CONFIRMED);
        try {
            requestsRepository.save(request);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 요청서 수락 실패!", e);
        }

        Event newEvent = Event.builder()
                .title(request.getMemberId() + "님의 " + request.getCompanyId() + "기업 요청서")
                .content(request.getContent())
                .status(EventsStatus.READY)
                .companyId(request.getCompanyId())
                .memberId(request.getMemberId())
                .build();
        try {
            eventsRepository.save(newEvent);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 이벤트 생성 실패!", e);
        }
    }

    // 요청서 거절
    @Override
    @Transactional
    public void rejectRequest(int requestId) {
        Requests request = requestsRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.setStatus(RequestsStatus.REJECTED);

        try {
            request = requestsRepository.save(request);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 요청서 거절 실패!", e);
        }

    }


    private void insertRequestCategory(int requestId, List<Integer> categoryId) {
        for (Integer categorysId : categoryId) {
            RequestCategory requestCategory = new RequestCategory();
            requestCategory.setRequestId(requestId);
            requestCategory.setCategoryId(categorysId);
            requestCategoryRepository.save(requestCategory);
        }
    }
}


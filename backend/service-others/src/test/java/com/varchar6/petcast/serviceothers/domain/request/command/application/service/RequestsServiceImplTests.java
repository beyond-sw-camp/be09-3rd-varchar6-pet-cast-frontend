package com.varchar6.petcast.serviceothers.domain.request.command.application.service;

import com.varchar6.petcast.serviceothers.domain.event.command.application.service.EventServiceImpl;
import com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.RequestCategory;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.Requests;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.EventsRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestCategoryRepository;
import com.varchar6.petcast.serviceothers.domain.request.command.domain.repository.RequestsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class RequestsServiceImplTests {

    private final RequestsRepository requestsRepository;
    private final EventsRepository eventsRepository;
    private final RequestCategoryRepository requestCategoryRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
//    @Qualifier("requestsService")
    @Autowired
    private RequestsService requestsService;

    @Autowired
    public RequestsServiceImplTests ( RequestsRepository requestsRepository,
                                      EventsRepository eventsRepository,
                                      RequestCategoryRepository requestCategoryRepository ) {
        this.requestsRepository = requestsRepository;
        this.eventsRepository = eventsRepository;
        this.requestCategoryRepository = requestCategoryRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    private RequestsServiceImpl requestService;

//    @BeforeEach
//    void setUp() {
//        modelMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT);
//        requestService = new RequestsServiceImpl (requestsRepository, modelMapper, requestCategoryRepository);
//    }

    @Test
    @DisplayName("요청서 작성 테스트")
    @Transactional
    void createRequestTest () {

        CreateRequestsRequestDTO requestDTO = new CreateRequestsRequestDTO ();
        requestDTO.setContent ( "Test Content" );
        requestDTO.setHopeCost ( 100000 );
        requestDTO.setHopeLocation ( "Test Location" );
        requestDTO.setHopeTime ( "2024-05-16'T'14:30:00" );
        requestDTO.setCompanyId ( 1 );
        requestDTO.setCategoryId ( Arrays.asList ( 1, 2 ) );

        int memberId = 1;
        Requests savedRequest = new Requests ();
        savedRequest.setId ( 1 );

        // When
        when ( requestsRepository.save ( any ( Requests.class ) ) ).thenReturn ( savedRequest );
        when ( requestCategoryRepository.existsById ( anyInt () ) ).thenReturn ( true );

        // Act
        requestService.createRequest ( requestDTO, memberId );

        // Then
        ArgumentCaptor<Requests> requestsCaptor = ArgumentCaptor.forClass ( Requests.class );
        verify ( requestsRepository ).save ( requestsCaptor.capture () );
        Requests capturedRequest = requestsCaptor.getValue ();

        assertEquals ( requestDTO.getContent (), capturedRequest.getContent () );
        assertEquals ( requestDTO.getHopeCost (), capturedRequest.getHopeCost () );
        assertEquals ( requestDTO.getHopeLocation (), capturedRequest.getHopeLocation () );
        assertEquals ( requestDTO.getHopeTime (), capturedRequest.getHopeTime () );
        assertEquals ( memberId, capturedRequest.getMemberId () );

        // Verify that each category is saved
        verify ( requestCategoryRepository, times ( requestDTO.getCategoryId ().size () ) ).save ( any ( RequestCategory.class ) );
    }




@Test
@DisplayName("요청서 삭제 테스트")
@Transactional
void deleteRequest () {
}

@Test
@DisplayName("요청서 수락 테스트")
@Transactional
void acceptRequest () {
}

@Test
@DisplayName("요청서 거절 테스트")
@Transactional
void rejectRequest () {
}
}
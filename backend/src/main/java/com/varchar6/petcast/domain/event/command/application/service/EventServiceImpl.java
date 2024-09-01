package com.varchar6.petcast.domain.event.command.application.service;

import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventSetStatusRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventUpdateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.response.EventResponseDTO;
import com.varchar6.petcast.domain.event.command.domain.aggregate.Event;
import com.varchar6.petcast.domain.event.command.domain.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "commandEventService")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EventResponseDTO updateEvent(EventUpdateRequestDTO updateRequestDTO) {
        Event event = eventRepository.findById(updateRequestDTO.getId()).orElseThrow();
        event.setContent(updateRequestDTO.getContent());

        EventResponseDTO responseDTO = modelMapper.map(event, EventResponseDTO.class);
        return responseDTO;
    }

    @Override
    @Transactional
    public void insertEvent(EventCreateRequestDTO eventCreateRequestDTO) {
        Event event = modelMapper.map(eventCreateRequestDTO, Event.class);
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public EventResponseDTO setEventStatus(EventSetStatusRequestDTO eventSetStatusRequestDTO) {
        Event event = eventRepository.findById(eventSetStatusRequestDTO.getId()).orElseThrow();
        event.setStatus(eventSetStatusRequestDTO.getStatus());
        EventResponseDTO responseDTO = modelMapper.map(event, EventResponseDTO.class);

        return responseDTO;
    }

}

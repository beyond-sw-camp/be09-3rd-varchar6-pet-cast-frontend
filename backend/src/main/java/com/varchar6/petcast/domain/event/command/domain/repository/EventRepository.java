package com.varchar6.petcast.domain.event.command.domain.repository;

import com.varchar6.petcast.domain.event.command.application.dto.response.EventResponseDTO;
import com.varchar6.petcast.domain.event.command.domain.aggregate.Event;
import com.varchar6.petcast.domain.notice.command.domain.aggregate.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e ORDER BY e.id DESC LIMIT 1")
    Event findLatestEvent();
}

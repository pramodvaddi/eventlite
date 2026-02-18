package com.pramodvaddiraju.eventlite.service;

import com.pramodvaddiraju.eventlite.dto.EventRequest;
import com.pramodvaddiraju.eventlite.dto.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);
    Page<EventResponse> getAllEvents(Pageable pageable);
    EventResponse getEventById(Long id);
    EventResponse updateEvent(Long id, EventRequest eventRequest);
    void deleteEvent(Long id);
}

package com.pramodvaddiraju.eventlite.service;

import com.pramodvaddiraju.eventlite.dto.EventRequest;
import com.pramodvaddiraju.eventlite.dto.EventResponse;
import com.pramodvaddiraju.eventlite.entity.Event;
import com.pramodvaddiraju.eventlite.exception.ResourceNotFoundException;
import com.pramodvaddiraju.eventlite.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepository){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = modelMapper.map(eventRequest, Event.class);
        Event saveEvent = eventRepository.save(event);
        log.info("Event saved by id: {}", event.getId());
        return modelMapper.map(saveEvent, EventResponse.class);
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        Page<Event> getAll = eventRepository.findAll(pageable);
        log.info("All events fetched");
        return getAll.map(m -> modelMapper.map(getAll, EventResponse.class));
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Not found with id: "+id)
        );
        log.info("Event fetched by id: {}", event.getId());
        return modelMapper.map(event, EventResponse.class);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found with id: "+id)
        );
        log.info("Update request initiated for event id:" + id);
        existingEvent.setTitle(eventRequest.getTitle());
        existingEvent.setDescription(eventRequest.getDescription());
        existingEvent.setEventDate(eventRequest.getEventDate());
        Event updatedEvent = eventRepository.save(existingEvent);
        log.info("Event successfully updated with id: {}", id);
        return modelMapper.map(updatedEvent, EventResponse.class);

    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found with id: "+id)
        );
        log.info("Event deleted with id: {}", event.getId());
        eventRepository.delete(event);
    }


//
//    @Override
//    public EventResponse createEvent(EventRequest eventRequest) {
//        Event event = modelMapper.map(eventRequest, Event.class);
//        Event createdEvent = eventRepository.save(event);
//        log.info("Event created successfully with id: {}", event.getId());
//        return modelMapper.map(createdEvent, EventResponse.class);
//    }
//
//    @Override
//    public Page<EventResponse> getAllEvents(Pageable pageable) {
//        Page<Event> event = eventRepository.findAll(pageable);
//        log.info("Events fetched successfully");
//        return event.map(e -> modelMapper.map(e, EventResponse.class));
//    }
//
//    @Override
//    public EventResponse getEventById(Long id) {
//        Event getById = eventRepository.findById(id).orElseThrow(
//                ()-> new ResourceNotFoundException("Not found with id: " + id)
//        );
//        return modelMapper.map(getById, EventResponse.class);
//    }
//
//    @Override
//    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
//        Event existingEvent = eventRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Event not found with id: " + id)
//        );
//        existingEvent.setTitle(eventRequest.getTitle());
//        existingEvent.setDescription(eventRequest.getDescription());
//        existingEvent.setEventDate(eventRequest.getEventDate());
//        Event updatedEvent = eventRepository.save(existingEvent);
//        log.info("Event updated successfully");
//
//        return modelMapper.map(updatedEvent, EventResponse.class);
//    }
//
//    @Override
//    public void deleteEvent(Long id) {
//        Event event = eventRepository.findById(id).orElseThrow(
//                ()-> new ResourceNotFoundException("Not found with id: " + id)
//        );
//        eventRepository.delete(event);
//        log.info("Event deleted successfully wit id: {}", id);
//    }
}

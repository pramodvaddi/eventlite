package com.pramodvaddiraju.eventlite.controller;

import com.pramodvaddiraju.eventlite.dto.EventRequest;
import com.pramodvaddiraju.eventlite.dto.EventResponse;
import com.pramodvaddiraju.eventlite.entity.Event;
import com.pramodvaddiraju.eventlite.response.ApiResponse;
import com.pramodvaddiraju.eventlite.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    ResponseEntity<ApiResponse<EventResponse>> createEvent(@Valid @RequestBody EventRequest eventRequest){
        EventResponse response = eventService.createEvent(eventRequest);
        ApiResponse<EventResponse> apiResponse = new ApiResponse<>(true, "Event Created", response);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    ResponseEntity<ApiResponse<Page<EventResponse>>> getAllEvents(Pageable pageable){
        Page<EventResponse> events = eventService.getAllEvents(pageable);
        ApiResponse<Page<EventResponse>> apiResponse = new ApiResponse<>(true, "Fetched all", events);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<EventResponse>> getById(@PathVariable Long id){
        EventResponse event = eventService.getEventById(id);
        ApiResponse<EventResponse> apiResponse = new ApiResponse<>(true, "Event fetched by id", event);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<EventResponse>> updateEvent(@Valid @PathVariable Long id, @RequestBody EventRequest eventRequest){
        EventResponse event = eventService.updateEvent(id, eventRequest);
        ApiResponse<EventResponse> apiResponse = new ApiResponse<>(true, "Event Updated Successfully", event);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(true, "Event Deleted Successfully", null);
        return ResponseEntity.ok(apiResponse);
    }






//
//    @PostMapping
//    ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest) {
//        return ResponseEntity.status(201).body(eventService.createEvent(eventRequest));
//    }
//
//    @GetMapping
//    ResponseEntity<Page<EventResponse>> getAllEvents(Pageable pageable){
//        return ResponseEntity.ok().body(eventService.getAllEvents(pageable));
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<EventResponse> getEventById(@PathVariable Long id){
//        return ResponseEntity.ok().body(eventService.getEventById(id));
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<EventResponse> updateEvent(@Valid @PathVariable Long id, @RequestBody EventRequest eventRequest){
//        return ResponseEntity.ok().body(eventService.updateEvent(id, eventRequest));
//    }
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> deleteEvent(@PathVariable Long id){
//        eventService.deleteEvent(id);
//        return ResponseEntity.noContent().build();
//    }







}

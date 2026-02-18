package com.pramodvaddiraju.eventlite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventRequest {

    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotNull(message = "date cannot be blank")
    private LocalDateTime eventDate;

    public EventRequest(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}

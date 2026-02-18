package com.pramodvaddiraju.eventlite.repository;

import com.pramodvaddiraju.eventlite.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {



}

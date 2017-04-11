package com.eretailservice.events.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eretailservice.events.domain.CalendarEvent;
import com.eretailservice.events.repositories.CalendarEventRepository;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
    private CalendarEventRepository eventRepository;

    public void setEventRepository(CalendarEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Iterable<CalendarEvent> listAllEvents() {
        return eventRepository.findAll();
    }
    
    public Iterable<CalendarEvent> findBetween(LocalDateTime start, LocalDateTime end){
    	return eventRepository.findBetween(start, end);
    }

    @Override
    public CalendarEvent getEventById(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public CalendarEvent saveEvent(CalendarEvent event) {
        return eventRepository.save(event);
    }
    
    public void deleteEvent(CalendarEvent event){
    	eventRepository.delete(event);
    };
}

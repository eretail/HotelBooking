package com.eretailservice.events.services;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.eretailservice.auth.model.User;
import com.eretailservice.auth.service.UserService;
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
    
/*    @Override
    public Iterable<CalendarEvent> listAllEventsByUser(String username){
    	return eventRepository.listAllEventsByUser(username);
    };*/
    
    public Iterable<CalendarEvent> findBetween(Date from, Date to) {
		return eventRepository.findBetween(from, to);
	};
	
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
    }

	@Override
	public Iterable<CalendarEvent> listEventsInRange(Date from, Date to) {
		return eventRepository.findBetween(from, to);
	};
	
	public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Timestamp)
            return ((java.sql.Timestamp) date).toLocalDateTime();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
    }
}
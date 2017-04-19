package com.eretailservice.events.services;


import java.sql.Date;

import com.eretailservice.events.domain.CalendarEvent;

public interface EventService {
    Iterable<CalendarEvent> listAllEvents();
    Iterable<CalendarEvent> listEventsInRange(Date from, Date to);
    CalendarEvent getEventById(Integer id);
    CalendarEvent saveEvent(CalendarEvent event);
    void deleteEvent(CalendarEvent event);
//	Iterable<CalendarEvent> listAllEventsByUser(String username);
}

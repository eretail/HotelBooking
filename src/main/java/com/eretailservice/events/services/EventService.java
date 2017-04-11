package com.eretailservice.events.services;


import com.eretailservice.events.domain.CalendarEvent;

public interface EventService {
    Iterable<CalendarEvent> listAllEvents();
    CalendarEvent getEventById(Integer id);
    CalendarEvent saveEvent(CalendarEvent event);
    void deleteEvent(CalendarEvent event);
}

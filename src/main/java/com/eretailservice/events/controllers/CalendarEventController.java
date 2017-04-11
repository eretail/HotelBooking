package com.eretailservice.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eretailservice.events.domain.CalendarEvent;
import com.eretailservice.events.services.EventService;

@Controller
public class CalendarEventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("events", eventService.listAllEvents());
        System.out.println("Returning events:");
        return "events";
    }

    @RequestMapping("event/{id}")
    public String showEvent(@PathVariable Integer id, Model model){
        model.addAttribute("event", eventService.getEventById(id));
        return "eventdetail";
    }

    @RequestMapping("event/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("event", eventService.getEventById(id));
        return "eventform";
    }

    @RequestMapping("event/new")
    public String newEvent(Model model){
        model.addAttribute("event", new CalendarEvent());
        return "eventform";
    }

    @RequestMapping(value = "event", method = RequestMethod.POST)
    public String saveEvent(CalendarEvent event){

        eventService.saveEvent(event);

        return "redirect:/event/" + event.getId();
    }

    @RequestMapping("event/delete/{id}")
    public String delete(@PathVariable Integer id){
    	return deleteEvent(eventService.getEventById(id));
    }
    
    @RequestMapping(value = "event", method = RequestMethod.DELETE)
    public String deleteEvent(CalendarEvent event){

        eventService.deleteEvent(event);

        return "redirect:/events/";
    }
    
    

}

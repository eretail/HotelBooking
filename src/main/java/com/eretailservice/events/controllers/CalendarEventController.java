package com.eretailservice.events.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eretailservice.auth.model.User;
import com.eretailservice.auth.service.UserService;
import com.eretailservice.events.domain.CalendarEvent;
import com.eretailservice.events.services.EventService;

@Controller
public class CalendarEventController {
    @Autowired
	private EventService eventService;

/*	@Autowired
    private UserService userService;
	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	User user = userService.findByUsername(username);
*/
/*    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
*/	

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String list(Model model){
      model.addAttribute("events", eventService.listAllEvents());
//    	model.addAttribute("events", eventService.listAllEventsByUser(username));
    	
        return "events";
    }
    
    @RequestMapping(value = "/events/range", method = RequestMethod.POST)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    public String listInRange(Model model, @RequestParam("fromDate") String from, @RequestParam("toDate") String to){
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	try {
	    	
    		java.util.Date parsedTo;
	        java.util.Date parsedFrom;
    		parsedFrom = format.parse(from);
			parsedTo = format.parse(to);

	    	Date fromDate = new Date(parsedFrom.getTime());
	        Date toDate = new Date(parsedTo.getTime());
	        
	        model.addAttribute("events", eventService.listEventsInRange(fromDate, toDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

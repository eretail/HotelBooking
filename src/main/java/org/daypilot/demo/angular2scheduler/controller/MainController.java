
package org.daypilot.demo.angular2scheduler.controller;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.daypilot.demo.angular2scheduler.domain.Event;
import org.daypilot.demo.angular2scheduler.domain.Resource;
import org.daypilot.demo.angular2scheduler.repository.EventRepository;
import org.daypilot.demo.angular2scheduler.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@RestController
public class MainController {
	
	@Autowired
	EventRepository er;
	
	@Autowired
	ResourceRepository rr;
	
    @RequestMapping("/api2")
    @ResponseBody
    String home() {
        return "Welcome!";
    }

    @RequestMapping("/api2/resources")
    Iterable<Resource> resources() {
    	return rr.findAll();    	
    }

    @GetMapping("/api2/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
    	return er.findBetween(from, to);    	
    }

    @PostMapping("/api2/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {
    	
    	Resource r = rr.findOne(params.resource);   	
    	
    	Event e = new Event();
    	e.setStart(params.start);
    	e.setEnd(params.end);
    	e.setText(params.text);
    	e.setResource(r);
    	
    	er.save(e);
    	
    	return e;
    }

    @PostMapping("/api2/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {
    	
    	Event e = er.findOne(params.id);   	
    	Resource r = rr.findOne(params.resource);
    	    	
    	e.setStart(params.start);
    	e.setEnd(params.end);
    	e.setResource(r);
    	
    	er.save(e);
    	
    	return e;
    }

    public static class EventCreateParams {
    	public LocalDateTime start; 
		public LocalDateTime end;
		public String text;
		public Long resource;
    }
    
    public static class EventMoveParams {
    	public Long id;
    	public LocalDateTime start; 
		public LocalDateTime end;
		public Long resource;
    }    

}
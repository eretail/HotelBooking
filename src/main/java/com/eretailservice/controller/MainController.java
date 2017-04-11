package com.eretailservice.controller;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eretailservice.model.Event;
import com.eretailservice.model.Resource;
import com.eretailservice.repository.EventRepository;
import com.eretailservice.repository.ResourceRepository;


@EnableJpaRepositories(
		basePackages = {"com.eretailservice.repository.EventRepository","com.eretailservice.repository.ResourceRepository"}
	)
@RestController
public class MainController {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@ResponseBody
	@RequestMapping("/api")
    String home() {
        return "Welcome!";
    }

    @RequestMapping("/api/resources")
    Iterable<Resource> resources() {
    	return resourceRepository.findAll();    	
    }

    @GetMapping("/api/events")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
    	return eventRepository.findBetween(from, to);    	
    }

    @PostMapping("/api/events/create")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {
    	
    	Resource r = resourceRepository.findOne(params.resource);   	
    	
    	Event e = new Event();
    	e.setStart(params.start);
    	e.setEnd(params.end);
    	e.setText(params.text);
    	e.setResource(r);
    	
    	eventRepository.save(e);
    	
    	return e;
    }

    @PostMapping("/api/events/move")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {
    	
    	Event e = eventRepository.findOne(params.id);   	
    	Resource r = resourceRepository.findOne(params.resource);
    	    	
    	e.setStart(params.start);
    	e.setEnd(params.end);
    	e.setResource(r);
    	
    	eventRepository.save(e);
    	
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
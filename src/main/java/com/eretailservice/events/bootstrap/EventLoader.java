package com.eretailservice.events.bootstrap;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.eretailservice.auth.model.User;
import com.eretailservice.auth.repository.UserRepository;
import com.eretailservice.events.domain.Calendar;
import com.eretailservice.events.domain.CalendarEvent;
import com.eretailservice.events.repositories.CalendarEventRepository;
import com.eretailservice.events.repositories.CalendarRepository;

@EnableJpaRepositories(
		basePackages = {"com.eretailservice.events.repositories"}
	)
@Component
public class EventLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private CalendarEventRepository eventRepository;
    
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private CalendarRepository calendarRepository;

    private Logger log = Logger.getLogger(EventLoader.class);

    /*
	    public void setEventRepository(CalendarEventRepository eventRepository) {
	        this.eventRepository = eventRepository;
	    }
    */

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	User yinchun = userRepository.findByUsername("yinchun");
    	Calendar yinchunCal=new Calendar("yinchunCalendar", yinchun);
    	
    	CalendarEvent getJobEvent = new CalendarEvent();
        getJobEvent.setEventId("#0000000#");
    	getJobEvent.setImageUrl("https://www.landscapingnetwork.com/img/mainphotoRevamp.jpg");
        getJobEvent.setDetailDescription("Yes, Please hire Yinchun, He is good man!");
        
        getJobEvent.setCalendar(yinchunCal);
        
        getJobEvent.setTitle("hiring");
        getJobEvent.setEventDateTime(LocalDateTime.now());
        getJobEvent.setLocation("Atlanta");

        String[] array = {"Yinchun","Yinchun's boss", "Yinchun's boss's boss"};
        getJobEvent.setAttendeeList(Stream.of(array).collect(Collectors.toSet()));
        getJobEvent.setReminderTime(LocalDateTime.now());
        getJobEvent.setHasReminderSent(false);
        
        calendarRepository.save(yinchunCal);
        eventRepository.save(getJobEvent);

        User tinRf = userRepository.findByUsername("tin");
    	Calendar tinCal=new Calendar("tinCal", yinchun);    	
    	CalendarEvent getTinRoofJobEvent = new CalendarEvent();
    	
    	getTinRoofJobEvent.setEventId("#0000001#");
    	getTinRoofJobEvent.setImageUrl("https://www.landscapingnetwork.com/img/mainphotoRevamp.jpg");
    	getTinRoofJobEvent.setDetailDescription("Yes, Please hire Yinchun, He is good man!");
        
    	getTinRoofJobEvent.setCalendar(tinCal);
        
    	getTinRoofJobEvent.setTitle("hiring");
    	getTinRoofJobEvent.setEventDateTime(LocalDateTime.now());
    	getTinRoofJobEvent.setLocation("Atlanta");

        String[] array2 = {"TinRoof","TinRoof's boss", "TinRoof's boss's boss"};
        getTinRoofJobEvent.setAttendeeList(Stream.of(array2).collect(Collectors.toSet()));
        getTinRoofJobEvent.setReminderTime(LocalDateTime.now());
        getTinRoofJobEvent.setHasReminderSent(false);
        
        calendarRepository.save(tinCal);        
        eventRepository.save(getTinRoofJobEvent);

        
        log.info("Saved Event - id: " + getJobEvent.getId());
    }
}
package com.eretailservice.events.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import com.eretailservice.events.domain.CalendarEvent;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer>{
	@Query("from CalendarEvent ce where not(ce.eventDateTime < :from or ce.eventDateTime > :to)")
	public Iterable<CalendarEvent> findBetween(@Param("from") @DateTimeFormat(pattern = "MM/dd/yyyy") Date start, 
			@Param("to") @DateTimeFormat(pattern = "MM/dd/yyyy") Date end);
	
//	@Query("from CalendarEvent ce inner join fetch ce.Calendar c inner join fetch c.User u where u.username = :username")
//	@Query("from User u join fetch u.Calendar c join fetch CalendarEvent ce where u.username = :username")
//	public Iterable<CalendarEvent> listAllEventsByUser(@Param("username") String username);
	
}

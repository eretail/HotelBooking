package com.eretailservice.events.repositories;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.eretailservice.events.domain.CalendarEvent;


public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Integer>{
	@Query("from CalendarEvent ce where not(ce.eventDateTime < :from or ce.eventDateTime > :to)")
	public Iterable<CalendarEvent> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start, 
			@Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
}

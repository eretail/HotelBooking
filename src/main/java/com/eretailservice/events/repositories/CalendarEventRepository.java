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
}

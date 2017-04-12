package com.eretailservice.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eretailservice.events.domain.Calendar;


public interface CalendarRepository extends JpaRepository<Calendar, Integer>{
	/*@Query("from Calendar c INNER JOIN User u where u.username = :username")
	public Calendar findCalendarByUsername(@Param("username") String username);*/
}

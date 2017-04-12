package com.eretailservice.events.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class CalendarEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String eventId;
    private String imageUrl;
    private String detailDescription;
    
    private String title; 
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date eventDateTime;
    private String location;
    
    @ManyToOne
    /*(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
	*/
    @JsonIgnore
	Calendar calendar;
	
	@JsonProperty("calendar")
	public Long getCalendarId() {
		return calendar.getId();
	}
    
    @Column
    @ElementCollection(targetClass=String.class)
    private Set<String> attendeeList;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @Temporal(TemporalType.DATE)
    private Date reminderTime;
    private boolean hasReminderSent;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDetailDescription() {
		return detailDescription;
	}
	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Set<String> getAttendeeList() {
		return attendeeList;
	}
	public void setAttendeeList(Set<String> attendeeList) {
		this.attendeeList = attendeeList;
	}
	public Date getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}
	public boolean isHasReminderSent() {
		return hasReminderSent;
	}
	public void setHasReminderSent(boolean hasReminderSent) {
		this.hasReminderSent = hasReminderSent;
	}
}

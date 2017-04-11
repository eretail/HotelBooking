package com.eretailservice.events.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.eretailservice.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Calendar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long Id;
	
	String name;
	
	public Calendar() {
		this.name="default";
	}
	
	public Calendar(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public Calendar(Long id) {
		this.Id = id;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	User user;
	
	@JsonProperty("user")
	public Long getUserId() {
		return user.getId();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
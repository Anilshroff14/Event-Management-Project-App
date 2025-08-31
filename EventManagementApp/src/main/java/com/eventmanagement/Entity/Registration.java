package com.eventmanagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate registration_Date;
	 
	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="attendee_id")
	private Attendee attendee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getRegistration_Date() {
		return registration_Date;
	}

	public void setRegistration_Date(LocalDate registration_Date) {
		this.registration_Date = registration_Date;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Attendee getAttendee() {
		return attendee;
	}

	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", registration_Date=" + registration_Date + "]";
	}

	
	
}

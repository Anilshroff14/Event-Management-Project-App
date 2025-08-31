package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.repository.EventRepository;

@Repository
public class EventDao {

	@Autowired
	private EventRepository eventRepository;

	// create event
	public Event saveEvent(Event event) {
		return eventRepository.save(event);

	}

	// getAllEvent
	public List<Event> getAllEvent() {
		return eventRepository.findAll();
	}

	// getEventById
	public Optional<Event> findById(Integer id) {
		return eventRepository.findById(id);
	}

	// updateEvent
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}

	// DeleteEvent
	public Optional<Event> deleteById(Integer id) {
		return eventRepository.findById(id);
	}

	// getAttendeeByEventId
	public List<Attendee> getAttendeeByEventId(Integer event_id) {
		return eventRepository.getAttendeeByEventId(event_id);
	}

	// getEventByOrganizerId
	public List<Event> getEventByOrganizerId(Integer organizer_Id) {
		return eventRepository.findByOrganizerId(organizer_Id);
	}

	// findEventByPaginationAndSorting
	public Page<Event> getEventByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return eventRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));

	}

}

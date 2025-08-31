package com.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;

	// create event
	@PostMapping
	public ResponseEntity<ResponseStructure<Event>> saveEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	// getAllEvent
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
		return eventService.getAllEvent();
	}

	// getEventById
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Event>> findEventById(@PathVariable Integer id) {
		return eventService.findEventById(id);
	}

	// updateEvent
	@PutMapping
	public ResponseEntity<ResponseStructure<Event>> updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	// DeleteEventById
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Event>> deleteEventById(@PathVariable Integer id) {
		return eventService.deleteEventById(id);
	}

	// GetAttendeeByEventId
	@GetMapping("/attendee/{event_id}")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(@PathVariable Integer event_id) {
		return eventService.getAttendeeByEventId(event_id);
	}

	// getEventByOrganizerId
	@GetMapping("/org_id/{organizer_id}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByOrganizerId(@PathVariable Integer organizer_id) {
		return eventService.getEventByOrganizerId(organizer_id);
	}

	// findByPaginationAndSorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Event>>> getEmployeeByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return eventService.getEventByPaginationAndSorting(pageNumber, pageSize, field);
	}

}

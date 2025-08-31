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
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.service.AttendeeService;
import com.eventmanagement.service.EventService;

@RestController
@RequestMapping("/attendee")
public class AttendeeController {
	@Autowired
	private AttendeeService attendeeService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Attendee>> saveAttendee(@RequestBody Attendee attendee) {
		return attendeeService.saveAttendee(attendee);
	}

	// getAllAttendee
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee() {
		return attendeeService.getAllAttendee();
	}

	// getAttendeeById
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> findAttendeeById(@PathVariable Integer id) {
		return attendeeService.findAttendeeById(id);
	}

	// updateAttendee
	@PutMapping
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(@RequestBody Attendee attendee) {
		return attendeeService.updateAttendee(attendee);
	}

	// DeleteAttendeeById
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> deleteAttendeeById(@PathVariable Integer id) {
		return attendeeService.deleteAttendeeById(id);
	}

	// getRegistrationByAttendeeId
	@GetMapping("/registration/{attendee_id}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(
			@PathVariable Integer attendee_id) {
		return attendeeService.getRegistrationByAttendeeId(attendee_id);
	}

	// getAttendeeByContact
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByContact(@PathVariable Long contact) {
		return attendeeService.getAttendeeByContact(contact);
	}

	// findAttendeeByPaginationAndSorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return attendeeService.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field);
	}

}

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
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private RegistrationService regService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(@RequestBody Registration reg) {
		return regService.saveRegistration(reg);
	}

	// getAllRegistration
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration() {
		return regService.getAllRegistration();
	}

	// getRegistrationById
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> findById(@PathVariable Integer id) {
		return regService.findRegistrationById(id);
	}

	// updateRegistration
	@PutMapping
	public ResponseEntity<ResponseStructure<Registration>> updateRegistration(@RequestBody Registration reg) {
		return regService.updateRegistration(reg);
	}

	// DeleteRegistrationById
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> deleteRegistrationById(@PathVariable Integer id) {
		return regService.deleteRegistrationById(id);
	}

	// getRegistrationByEventId
	@GetMapping("/event/{event_id}")
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationByEventId(@PathVariable Integer event_id) {
		return regService.findRegistrationByEventId(event_id);
	}

	// getRegistrationByAttendeeId
	@GetMapping("/attendee/{attendee_id}")
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationByAttendeeId(@PathVariable Integer attendee_id) {
		return regService.findRegistrationByAttendeeId(attendee_id);
	}

	// findRegistrationByPaginationAndSorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Registration>>> getRegistrationByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return regService.getRegistrationByPaginationAndSorting(pageNumber, pageSize, field);
	}

}

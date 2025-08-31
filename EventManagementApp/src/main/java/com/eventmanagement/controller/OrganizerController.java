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

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.OrganizerService;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
	@Autowired
	private OrganizerService orgService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Organizer>> saveOrganizer(@RequestBody Organizer org) {
		return orgService.saveOrganizer(org);
	}

	// getAllEvent
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer() {
		return orgService.getAllOrganizer();
	}

	// getOrganizerById
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> findOrganizerById(@PathVariable Integer id) {
		return orgService.findOrganizerById(id);
	}

	// updateOrganizer
	@PutMapping
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(@RequestBody Organizer org) {
		return orgService.updateOrganizer(org);
	}

	// DeleteOrganizerById
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> deleteOrganizerById(@PathVariable Integer id) {
		return orgService.deleteOrganizerById(id);
	}

	// findOrganizerByPaginationAndSorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return orgService.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field);
	}

}

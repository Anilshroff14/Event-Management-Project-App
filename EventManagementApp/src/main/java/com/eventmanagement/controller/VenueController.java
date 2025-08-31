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
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.VenueService;

@RestController
@RequestMapping("/venue")
public class VenueController {
	@Autowired
	private VenueService venueService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(@RequestBody Venue venue) {
		return venueService.saveVenue(venue);
	}

	// getAllVenue
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue() {
		return venueService.getAllVenue();
	}

	// getVenueById
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Venue>> findVenueById(@PathVariable Integer id) {
		return venueService.findVenueById(id);
	}

	// updateVenue
	@PutMapping
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(@RequestBody Venue venue) {
		return venueService.updateVenue(venue);
	}

	// DeleteVenueById
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Venue>> deleteVenueById(@PathVariable Integer id) {
		return venueService.deleteVenueById(id);
	}

	// getEventByVenueId
	@GetMapping("/event/{venue_id}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByVenueId(@PathVariable Integer venue_id) {
		return venueService.getEventByVenueId(venue_id);
	}

	// getVenueByLocation
	@GetMapping("/loc/{location}")
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(@PathVariable String location) {
		return venueService.getVenueByLocation(location);
	}

	// findVenueByPaginationAndSorting
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return venueService.getVenueByPaginationAndSorting(pageNumber, pageSize, field);
	}

}

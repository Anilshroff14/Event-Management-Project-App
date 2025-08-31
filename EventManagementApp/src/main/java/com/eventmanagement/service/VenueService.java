package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dao.VenueDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.repository.VenueRepository;

@Service
public class VenueService {
	@Autowired
	private VenueDao venueDao;

	// createVenue
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(Venue venue) {
		Venue saveVenue = venueDao.saveVenue(venue);
		ResponseStructure<Venue> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Venue saved successfully");
		structure.setData(saveVenue);
		return new ResponseEntity<ResponseStructure<Venue>>(structure, HttpStatus.CREATED);
	}

	// getAllVenue
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue() {
		List<Venue> venue = venueDao.getAllVenue();
		ResponseStructure<List<Venue>> structure = new ResponseStructure<>();
		if (!venue.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue fetched successfully");
			structure.setData(venue);
			return new ResponseEntity<ResponseStructure<List<Venue>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Venue available");
		}
	}

	// getVanueByVenueId
	public ResponseEntity<ResponseStructure<Venue>> findVenueById(Integer id) {
		ResponseStructure<Venue> structure = new ResponseStructure<>();
		Optional<Venue> opt = venueDao.findById(id);
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Venue with ID " + id + " not found");
		}
	}

	// updateEvent
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(Venue venue) {
		Venue updatedVenue = venueDao.saveVenue(venue);
		ResponseStructure<Venue> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Event updated successfully");
		structure.setData(updatedVenue);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	/// DeleteEventById
	public ResponseEntity<ResponseStructure<Venue>> deleteVenueById(Integer id) {
		ResponseStructure<Venue> structure = new ResponseStructure<>();
		Optional<Venue> opt = venueDao.findById(id);
		if (opt.isPresent()) {
			venueDao.deleteById(id); // Actual deletion
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue deleted successfully");
			structure.setData(opt.get()); // Returning deleted data
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Venue with ID " + id + " not found");
		}
	}

	// getEventByVenueId
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByVenueId(Integer venue_id) {
		List<Event> event = venueDao.getEventByVenueId(venue_id);
		ResponseStructure<List<Event>> structure = new ResponseStructure<>();
		if (!event.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event Fetched successfully");
			structure.setData(event);
			return new ResponseEntity<ResponseStructure<List<Event>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Event With VenueId " + venue_id + "not found");
		}
	}

	// getVenueByLocation
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(String location) {
		List<Venue> venue = venueDao.getVenueByLocation(location);
		ResponseStructure<List<Venue>> structure = new ResponseStructure<List<Venue>>();
		if (!venue.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Venue fetched successfully");
			structure.setData(venue);
			return new ResponseEntity<ResponseStructure<List<Venue>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Venue with Location " + location + " not found");
		}
	}

	// findVenueByPaginationAndSorting
	public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		Page<Venue> venue = venueDao.getVenueByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Venue>> structure = new ResponseStructure<Page<Venue>>();
		if (!venue.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee sort and in ascending order fetched successfully");
			structure.setData(venue);
			return new ResponseEntity<ResponseStructure<Page<Venue>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No venue available");
		}
	}

}

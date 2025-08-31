package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.OrganizerRepository;
import com.eventmanagement.repository.VenueRepository;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;

	@Autowired
	private OrganizerRepository orgRepository;

	@Autowired
	private VenueRepository venueRepository;

	// create Event

	public ResponseEntity<ResponseStructure<Event>> saveEvent(Event event) {
//		Optional<Organizer> optOrganizer = orgRepository.findById(event.getOrganizer().getId());
//		event.setOrganizer(optOrganizer.get());

//		Optional<Venue> optVenue = venueRepository.findById(event.getVenue().getId());
//		event.setVenue(optVenue.get());

		Organizer org = orgRepository.findById(event.getOrganizer().getId()).orElse(null);
		Venue venue = venueRepository.findById(event.getVenue().getId()).orElse(null);

		if (org == null || venue == null) {
			throw new IdNotFoundException("Organizer or Venue ID not found");
		}

		event.setOrganizer(org);
		event.setVenue(venue);

		Event saveEvent = eventDao.saveEvent(event);
		ResponseStructure<Event> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Event saved successfully");
		structure.setData(saveEvent);

		return new ResponseEntity<ResponseStructure<Event>>(structure, HttpStatus.CREATED);
	}

	// getAllEvent
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
		List<Event> event = eventDao.getAllEvent();

		ResponseStructure<List<Event>> structure = new ResponseStructure<>();
		if (!event.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Events fetched successfully");
			structure.setData(event);
			return new ResponseEntity<ResponseStructure<List<Event>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No employees available");
		}
	}

	// findEventById
	public ResponseEntity<ResponseStructure<Event>> findEventById(Integer id) {
		ResponseStructure<Event> structure = new ResponseStructure<>();

		Optional<Event> opt = eventDao.findById(id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Event with ID " + id + " not found");
		}
	}

	// updateEvent
	public ResponseEntity<ResponseStructure<Event>> updateEvent(Event event) {
		Event updatedEvent = eventDao.saveEvent(event);

		ResponseStructure<Event> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Event updated successfully");
		structure.setData(updatedEvent);

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	/// DeleteEventById
	public ResponseEntity<ResponseStructure<Event>> deleteEventById(Integer id) {
		ResponseStructure<Event> structure = new ResponseStructure<>();

		Optional<Event> opt = eventDao.findById(id);

		if (opt.isPresent()) {
			eventDao.deleteById(id); // Actual deletion
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event deleted successfully");
			structure.setData(opt.get()); // Returning deleted data
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Event with ID " + id + " not found");
		}
	}

	// GetAttendeeByEventId
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(Integer event_id) {
		List<Attendee> list = eventDao.getAttendeeByEventId(event_id);

		ResponseStructure<List<Attendee>> structure = new ResponseStructure<List<Attendee>>();
		if (!list.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Event fetched successfully");
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Attendee>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Attendee with EventID " + event_id + " not found");
		}
	}

	// getEventByOrganizer_id
	public ResponseEntity<ResponseStructure<List<Event>>> getEventByOrganizerId(Integer organizer_id) {
		List<Event> list = eventDao.getEventByOrganizerId(organizer_id);

		if (!list.isEmpty()) {
			ResponseStructure<List<Event>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Events fetched successfully");
			structure.setData(list);
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No events found for organizer ID " + organizer_id);
		}
	}

	// findEventByPaginationAndSorting
	public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(int pageNumber, int pageSize,
			String field) {
		Page<Event> event = eventDao.getEventByPaginationAndSorting(pageNumber, pageSize, field);

		ResponseStructure<Page<Event>> structure = new ResponseStructure<Page<Event>>();
		if (!event.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee sort and in ascending order fetched successfully");
			structure.setData(event);
			return new ResponseEntity<ResponseStructure<Page<Event>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Event available");
		}
	}

}

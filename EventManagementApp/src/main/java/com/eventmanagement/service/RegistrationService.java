package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dao.RegistrationDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.repository.AttendeeRepository;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	private RegistrationDao regDao;

	@Autowired
	private AttendeeRepository attendeeRepository;

	@Autowired
	private EventRepository eventRepository;

	// create Event

	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(Registration reg) {

		Optional<Attendee> optAttendee = attendeeRepository.findById(reg.getAttendee().getId());
		reg.setAttendee(optAttendee.get());

		Optional<Event> optEvent = eventRepository.findById(reg.getEvent().getId());
		reg.setEvent(optEvent.get());

		Registration saveRegistration = regDao.saveRegistration(reg);
		ResponseStructure<Registration> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Registration saved successfully");
		structure.setData(saveRegistration);

		return new ResponseEntity<ResponseStructure<Registration>>(structure, HttpStatus.CREATED);
	}

	// getAllRegistration
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistration() {
		List<Registration> reg = regDao.getAllRegistration();

		ResponseStructure<List<Registration>> structure = new ResponseStructure<>();
		if (!reg.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Events fetched successfully");
			structure.setData(reg);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No employees available");
		}
	}

	// findById
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationById(Integer id) {
		ResponseStructure<Registration> structure = new ResponseStructure<>();

		Optional<Registration> opt = regDao.findById(id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Registration with ID " + id + " not found");
		}
	}

	// updateRegistration
	public ResponseEntity<ResponseStructure<Registration>> updateRegistration(Registration reg) {
		Registration updatedRegistration = regDao.saveRegistration(reg);

		ResponseStructure<Registration> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Registration updated successfully");
		structure.setData(updatedRegistration);

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	/// Delete Registration
	public ResponseEntity<ResponseStructure<Registration>> deleteRegistrationById(Integer id) {
		ResponseStructure<Registration> structure = new ResponseStructure<>();

		Optional<Registration> opt = regDao.findById(id);

		if (opt.isPresent()) {
			regDao.deleteById(id); // Actual deletion
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration deleted successfully");
			structure.setData(opt.get()); // Returning deleted data
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Registration with ID " + id + " not found");
		}
	}

	// findRegistrationByEventId
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationByEventId(Integer event_id) {
		ResponseStructure<Registration> structure = new ResponseStructure<>();

		Optional<Registration> opt = regDao.findRegistrationByEventId(event_id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Registration with event_ID " + event_id + " not found");
		}
	}

	// findRegistrationByAttendeeId
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationByAttendeeId(Integer attendee_id) {
		ResponseStructure<Registration> structure = new ResponseStructure<>();
		Optional<Registration> optAttendee = regDao.findRegistrationByAttendeeId(attendee_id);

		if (optAttendee.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration fetched successfully");
			structure.setData(optAttendee.get()); // no need for getById()

			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Registration with Attendee_ID " + attendee_id + " not found");
		}
	}

	// findRegistrationByPaginationAndSorting
	public ResponseEntity<ResponseStructure<Page<Registration>>> getRegistrationByPaginationAndSorting(int pageNumber,
			int pageSize, String field) {
		Page<Registration> reg = regDao.getRegistrationByPaginationAndSorting(pageNumber, pageSize, field);

		ResponseStructure<Page<Registration>> structure = new ResponseStructure<Page<Registration>>();
		if (!reg.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration sort and in ascending order fetched successfully");
			structure.setData(reg);
			return new ResponseEntity<ResponseStructure<Page<Registration>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Registration available");
		}
	}

}

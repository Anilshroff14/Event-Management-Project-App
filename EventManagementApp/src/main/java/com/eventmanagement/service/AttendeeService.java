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
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dao.AttendeeDao;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.repository.AttendeeRepository;

@Service
public class AttendeeService {
	@Autowired
	private AttendeeDao attendeeDao;

	// create Attendee
	public ResponseEntity<ResponseStructure<Attendee>> saveAttendee(Attendee attendee) {
		Attendee saveAttendee = attendeeDao.saveAttendee(attendee);
		ResponseStructure<Attendee> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Event saved successfully");
		structure.setData(saveAttendee);

		return new ResponseEntity<ResponseStructure<Attendee>>(structure, HttpStatus.CREATED);
	}

	// getAllAttendee
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee() {
		List<Attendee> event = attendeeDao.getAllAttendee();

		ResponseStructure<List<Attendee>> structure = new ResponseStructure<>();
		if (!event.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee fetched successfully");
			structure.setData(event);
			return new ResponseEntity<ResponseStructure<List<Attendee>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Attendee available");
		}
	}

	// findAttendeeById

	public ResponseEntity<ResponseStructure<Attendee>> findAttendeeById(Integer id) {
		ResponseStructure<Attendee> structure = new ResponseStructure<>();

		Optional<Attendee> opt = attendeeDao.findById(id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Attendee with ID " + id + " not found");
		}
	}

	// updateAttendee
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Attendee attendee) {
		Attendee updatedAttendee = attendeeDao.saveAttendee(attendee);

		ResponseStructure<Attendee> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Attendee updated successfully");
		structure.setData(updatedAttendee);

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	/// deleteAttendeeById
	public ResponseEntity<ResponseStructure<Attendee>> deleteAttendeeById(Integer id) {
		ResponseStructure<Attendee> structure = new ResponseStructure<>();

		Optional<Attendee> opt = attendeeDao.findById(id);

		if (opt.isPresent()) {
			attendeeDao.deleteById(id); // Actual deletion
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee deleted successfully");
			structure.setData(opt.get()); // Returning deleted data
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Attendee with ID " + id + " not found");
		}
	}

	// getRegistrationByAttendeeId
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendeeId(Integer attendee_id) {
		List<Registration> reg = attendeeDao.getRegistrationByAttendeeId(attendee_id);
		ResponseStructure<List<Registration>> structure = new ResponseStructure<>();

		if (!reg.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Registration fetched successfully");
			structure.setData(reg);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Registration With AttendeeId " + attendee_id + "not found");
		}
	}

	// getAttendeeByContact
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByContact(Long contact) {
		ResponseStructure<Attendee> structure = new ResponseStructure<>();

		Optional<Attendee> opt = attendeeDao.getAttendeeByContact(contact);
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee Found Successfully");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Attendee>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Attendee with Contact " + contact + "not found");

		}
	}

	// findAttendeeByPaginationAndSorting
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(int pageNumber,
			int pageSize, String field) {
		Page<Attendee> attendee = attendeeDao.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field);

		ResponseStructure<Page<Attendee>> structure = new ResponseStructure<Page<Attendee>>();
		if (!attendee.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Attendee sort and in ascending order fetched successfully");
			structure.setData(attendee);
			return new ResponseEntity<ResponseStructure<Page<Attendee>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Attendee available");
		}
	}

}

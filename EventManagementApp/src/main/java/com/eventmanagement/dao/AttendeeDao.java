package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.repository.AttendeeRepository;
import com.eventmanagement.repository.EventRepository;

@Repository
public class AttendeeDao {

	@Autowired
	private AttendeeRepository attendeeRepository;

	public Attendee saveAttendee(Attendee attendee) {
		return attendeeRepository.save(attendee);

	}

	// getAllAttendee
	public List<Attendee> getAllAttendee() {
		return attendeeRepository.findAll();
	}

	// getAttendeeById
	public Optional<Attendee> findById(Integer id) {
		return attendeeRepository.findById(id);
	}

	// updateAttendee
	public Attendee updateAttendee(Attendee attendee) {
		return attendeeRepository.save(attendee);
	}

	// updateAttendee
	public Optional<Attendee> deleteById(Integer id) {
		return attendeeRepository.findById(id);
	}

	// getRegistrationByAttendeeId
	public List<Registration> getRegistrationByAttendeeId(Integer attendee_id) {
		return attendeeRepository.getRegistrationByAttendeeId(attendee_id);
	}

	// getAttendeeByContact
	public Optional<Attendee> getAttendeeByContact(Long contact) {
		return attendeeRepository.getAttendeeByContact(contact);
	}

	// findAttendeeByPaginationAndSorting
	public Page<Attendee> getAttendeeByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return attendeeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));

	}

}

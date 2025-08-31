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
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;

@Repository
public class RegistrationDao {

	@Autowired
	private RegistrationRepository regRepository;

	// create registration
	public Registration saveRegistration(Registration reg) {
		return regRepository.save(reg);

	}

	// getAllRegistration
	public List<Registration> getAllRegistration() {
		return regRepository.findAll();
	}

	// getRegistrationById
	public Optional<Registration> findById(Integer id) {
		return regRepository.findById(id);
	}

	// updateRegistration
	public Registration updateRegistration(Registration reg) {
		return regRepository.save(reg);
	}

	// deleteRegistration
	public Optional<Registration> deleteById(Integer id) {
		return regRepository.findById(id);
	}

	// getRegistrationByEventId
	public Optional<Registration> findRegistrationByEventId(Integer event_id) {
		return regRepository.findRegistrationByEventId(event_id);
	}

	// getRegistrationByAttendeeId
	public Optional<Registration> findRegistrationByAttendeeId(Integer attendee_id) {
		return regRepository.findRegistrationByAttendeeId(attendee_id);
	}

	// findRegistrationByPaginationAndSorting
	public Page<Registration> getRegistrationByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return regRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));

	}

}

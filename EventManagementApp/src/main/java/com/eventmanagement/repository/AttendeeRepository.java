package com.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {

	Optional<Attendee> getAttendeeByContact(Long contact);

	@Query("select r from Registration r where r.attendee.id=?1")
	List<Registration> getRegistrationByAttendeeId(Integer id);

}

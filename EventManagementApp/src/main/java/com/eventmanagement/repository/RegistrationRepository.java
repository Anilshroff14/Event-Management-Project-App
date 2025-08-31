package com.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	Optional<Registration> findRegistrationByEventId(Integer event_id);

	Optional<Registration> findRegistrationByAttendeeId(Integer attendee_id);

}

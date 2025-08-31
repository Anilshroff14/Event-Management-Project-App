package com.eventmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	List<Event> findByOrganizerId(Integer organizer_id);

	@Query("select r.attendee from Registration r  where r.event.id=?1")
	List<Attendee> getAttendeeByEventId(Integer id);

}

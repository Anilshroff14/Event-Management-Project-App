package com.eventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

	List<Venue> findVenueByLocation(String location);

	@Query("select e from Event e where e.venue.id=?1")
	List<Event> getEventByVenueId(Integer id);

}

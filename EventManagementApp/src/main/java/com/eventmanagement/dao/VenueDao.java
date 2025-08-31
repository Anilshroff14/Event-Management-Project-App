package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.VenueRepository;

@Repository
public class VenueDao {

	@Autowired
	private VenueRepository venueRepository;

	// create venue
	public Venue saveVenue(Venue venue) {
		return venueRepository.save(venue);
	}

	// getAllVenue
	public List<Venue> getAllVenue() {
		return venueRepository.findAll();
	}

	// getVenueById
	public Optional<Venue> findById(Integer id) {
		return venueRepository.findById(id);
	}

	// updateVenue
	public Venue updateVenue(Venue venue) {
		return venueRepository.save(venue);
	}

	// deleteVenue
	public Optional<Venue> deleteById(Integer id) {
		return venueRepository.findById(id);
	}

	// getEventByVenueId
	public List<Event> getEventByVenueId(Integer venue_id) {
		return venueRepository.getEventByVenueId(venue_id);
	}

	// getVenueByLocation
	public List<Venue> getVenueByLocation(String location) {
		return venueRepository.findVenueByLocation(location);
	}

	// findVenueByPaginationAndSorting
	public Page<Venue> getVenueByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return venueRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));

	}

}

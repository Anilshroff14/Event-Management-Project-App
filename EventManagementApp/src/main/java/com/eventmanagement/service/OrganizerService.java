package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dao.OrganizerDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.repository.OrganizerRepository;

@Service
public class OrganizerService {
	@Autowired
	private OrganizerDao orgDao;

	// create Organizer
	public ResponseEntity<ResponseStructure<Organizer>> saveOrganizer(Organizer org) {
		Organizer saveOrganizer = orgDao.saveOrganizer(org);
		ResponseStructure<Organizer> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Event saved successfully");
		structure.setData(saveOrganizer);

		return new ResponseEntity<ResponseStructure<Organizer>>(structure, HttpStatus.CREATED);
	}

	// getAllOrganizer
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer() {
		List<Organizer> org = orgDao.getAllOrganizer();

		ResponseStructure<List<Organizer>> structure = new ResponseStructure<>();
		if (!org.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Events fetched successfully");
			structure.setData(org);
			return new ResponseEntity<ResponseStructure<List<Organizer>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Organizer available");
		}
	}

	// findOrganizerById
	public ResponseEntity<ResponseStructure<Organizer>> findOrganizerById(Integer id) {
		ResponseStructure<Organizer> structure = new ResponseStructure<>();

		Optional<Organizer> opt = orgDao.findById(id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Organizer fetched successfully");
			structure.setData(opt.get()); // no need for getById()
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Organizer with ID " + id + " not found");
		}
	}

	// updateOrganizer
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Organizer org) {
		Organizer updatedOrganizer = orgDao.saveOrganizer(org);
		ResponseStructure<Organizer> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Organizer updated successfully");
		structure.setData(updatedOrganizer);

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	/// DeleteEventById
	public ResponseEntity<ResponseStructure<Organizer>> deleteOrganizerById(Integer id) {
		ResponseStructure<Organizer> structure = new ResponseStructure<>();

		Optional<Organizer> opt = orgDao.findById(id);

		if (opt.isPresent()) {
			orgDao.deleteById(id); // Actual deletion
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Organizer deleted successfully");
			structure.setData(opt.get()); // Returning deleted data
			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Organizer with ID " + id + " not found");
		}
	}

	// findOrganizerByPaginationAndSorting
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(int pageNumber,
			int pageSize, String field) {
		Page<Organizer> org = orgDao.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field);

		ResponseStructure<Page<Organizer>> structure = new ResponseStructure<Page<Organizer>>();
		if (!org.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Organizer sort and in ascending order fetched successfully");
			structure.setData(org);
			return new ResponseEntity<ResponseStructure<Page<Organizer>>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No Organizer available");
		}
	}

}

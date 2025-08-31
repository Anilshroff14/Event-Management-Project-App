package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.OrganizerRepository;

@Repository
public class OrganizerDao {

	@Autowired
	private OrganizerRepository orgRepository;

	// create organizer
	public Organizer saveOrganizer(Organizer org) {
		return orgRepository.save(org);

	}

	// getAllOrganizer
	public List<Organizer> getAllOrganizer() {
		return orgRepository.findAll();
	}

	// getOrganizerById
	public Optional<Organizer> findById(Integer id) {
		return orgRepository.findById(id);
	}

	// updateOrganizer
	public Organizer updateOrganizer(Organizer org) {
		return orgRepository.save(org);
	}

	// deleteOrganizer
	public Optional<Organizer> deleteById(Integer id) {
		return orgRepository.findById(id);
	}

	// findOrganizerByPaginationAndSorting
	public Page<Organizer> getOrganizerByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return orgRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));

	}

}

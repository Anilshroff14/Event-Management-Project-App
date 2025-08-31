package com.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.Entity.Event;
import com.eventmanagement.Entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

}

package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    public Organizer findOrganizerByEmailAndUserName(String email, String userName);
}
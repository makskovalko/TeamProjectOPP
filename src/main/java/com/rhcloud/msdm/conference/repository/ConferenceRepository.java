package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {



}
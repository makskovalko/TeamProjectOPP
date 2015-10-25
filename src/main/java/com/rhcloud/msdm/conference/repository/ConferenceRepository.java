package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    List<Conference> findAllConferencesByOrganizerId(Integer organizerId);
    List<Conference> findAllConferencesByName(String name);

    List<Conference> findAllConferenceByIdBetween(Integer startID, Integer endID );

    @Query("select max(c.id) from Conference c")
    Integer findMaxID();
}
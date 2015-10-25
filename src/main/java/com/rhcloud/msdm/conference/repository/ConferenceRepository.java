package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    public List<Conference> findAllConferencesByOrganizerId(Integer organizerId);
    public List<Conference> findAllConferencesByName(String name);
    public List<Conference> findAllConferencesByNameContaining(String name);
}
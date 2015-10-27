package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    public List<Conference> findAllConferencesByNameContaining(String name);

    List<Conference> findAllConferencesByOrganizerId(Integer organizerId);

    List<Conference> findAllConferencesByName(String name);

    List<Conference> findAllConferenceByIdBetween(Integer startID, Integer endID);

    @Query("select max(c.id) from Conference c")
    Integer findMaxID();

    @Query("update Conference c set c.participantCount = c.participantCount + 1 where c.id = ?1")
    @Modifying
    @Transactional
    void buyTicket(Integer id);

    List<Conference> findConferencesByCountryAndCity(String country, String city);
    List<Conference> findConferencesByCountryOrCity(String country, String city);

    List<Conference> findByCategory_IdIn(List<Integer> categoryId);

    List<Conference> findConferencesByCountryAndCityAndCategory_IdIn(String country, String city, List<Integer> categoryId);
    List<Conference> findConferencesByCountryOrCityAndCategory_IdIn(String country, String city, List<Integer> categoryId);
}
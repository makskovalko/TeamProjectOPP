package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    public Organizer findOrganizerByEmailAndUserName(String email, String userName);

    @Modifying
    @Transactional
    @Query("update Organizer o set o.confirmationKey = '', o.active = 1 where o.userName = ?1 and o.confirmationKey = ?2")
    public void confirmRegistration(String userName, String confirmationKey);
}
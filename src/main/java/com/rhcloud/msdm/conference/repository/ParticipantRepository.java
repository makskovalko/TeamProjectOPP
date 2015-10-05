package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    public Participant findParticipantByEmailAndUserName(String email, String userName);

    @Modifying
    @Transactional
    @Query("update Participant p set p.confirmationKey = '', p.active = 1 where p.userName = ?1 and p.confirmationKey = ?2")
    public void confirmRegistration(String userName, String confirmationKey);
}
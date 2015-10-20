package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    Participant findParticipantByEmailOrUserName(String email, String userName);

    Participant findParticipantByUserNameAndPassword(String userName, String password);

    @Modifying
    @Transactional
    @Query("update Participant p set p.confirmationKey = '', p.active = 1 where p.userName = ?1 and p.confirmationKey = ?2")
    void confirmRegistration(String userName, String confirmationKey);

}
package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    Participant findParticipantByEmailOrUserName(String email, String userName);

    Participant findParticipantByUserNameAndPassword(String userName, String password);

    @Modifying
    @Transactional
    @Query("update Participant p set p.confirmationKey = '', p.active = 1 where p.userName = ?1 and p.confirmationKey = ?2")
    void confirmRegistration(String userName, String confirmationKey);


    @Modifying
    @Transactional
    @Query("update Participant p set p.userName = :log, p.firstName = :fN, p.lastName = :lN, p.phoneNumber = :number," +
            "p.profileImage = :img, p.dateOfBirth = :img  where p.id = :id")
    void editInformation(@Param("id") Integer id,
                         @Param("log") String userName,
                         @Param("fN") String firstName,
                         @Param("lN") String lastName,
                         @Param("number") String phoneNumber,
                         @Param("img") String profileImage,
                         @Param("dateOfBirth") Date dateOfBirth
    );


    @Modifying
    @Transactional
    @Query("update Participant p set p.password = ?2 where p.id = ?1")
    void editPassword(Integer id, String password);


    @Modifying
    @Transactional
    @Query("update Participant p set p.email = ?2 where p.id = ?1")
    void editEmail(Integer id, String email);

}
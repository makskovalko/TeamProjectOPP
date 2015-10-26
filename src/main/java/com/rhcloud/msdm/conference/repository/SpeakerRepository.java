package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
    public Speaker findSpeakerByEmailOrUserName(String email, String userName);
    public Speaker findSpeakerByUserNameAndPassword(String userName, String password);

    @Modifying
    @Transactional
    @Query("update Speaker s set s.confirmationKey = '', s.active = 1 where s.userName = ?1 and s.confirmationKey = ?2")
    public void confirmRegistration(String userName, String confirmationKey);


    @Modifying
    @Transactional
    @Query("update Speaker s set s.profileImage=?2 where s.id = ?1")
    public void  updatePhoto(Integer id, String photoImage);



    @Transactional
    @Query(value = "select  s from  Speaker s join  fetch s.conferences c where s.id = ?1",
            countQuery = "select count(s) from Speaker s where s.id= ?1"
    )
    public Speaker getSpeakerJoinConferenceById(Integer id);


    @Transactional
    @Query(value = "select  s from  Speaker s left join  fetch s.offers o join  fetch o.conference where s.id = ?1",
            countQuery = "select count(s) from Speaker s where s.id= ?1"
    )
    public Speaker getSpeakerJoinOffersById(Integer id);



    @Transactional
    @Query(value = "select  s from  Speaker s left join  fetch s.queryConferenceList q where s.id = ?1",
            countQuery = "select count(s) from Speaker s where s.id= ?1"
    )
    public Speaker getSpeakerJoinQueryToConferencesById(Integer id);


    @Transactional
    @Query("select  s from  Speaker s WHERE s.userName=?1 ")
    public  Speaker getByUserName(String userName);



}
package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
    public Speaker findSpeakerByEmailAndUserName(String email, String userName);

    @Modifying
    @Transactional
    @Query("update Speaker s set s.confirmationKey = '', s.active = 1 where s.userName = ?1 and s.confirmationKey = ?2")
    public void confirmRegistration(String userName, String confirmationKey);
}
package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {

}

package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.QueryConference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ghost on 23.10.2015.
 */

public interface QueryConferenceRepository extends JpaRepository<QueryConference, Integer>
{

    @Override
    @Transactional
    @Query(value = "SELECT q FROM  QueryConference q  JOIN  FETCH  q.speaker  speaker  JOIN  FETCH  q.conference  WHERE q.id = ?1")
    public  QueryConference findOne(Integer id);



}

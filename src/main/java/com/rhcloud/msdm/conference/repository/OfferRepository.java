package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ghost on 18.10.2015.
 */

public interface OfferRepository  extends JpaRepository<Offer, Integer>
{
    @Override
    @Transactional
    @Query("SELECT o FROM  Offer o JOIN  FETCH  o.speaker  speaker   JOIN  FETCH  o.conference  conference  WHERE  o.id = ?1")
    public  Offer findOne(Integer id);



}

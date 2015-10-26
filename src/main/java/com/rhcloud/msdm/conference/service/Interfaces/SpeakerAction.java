package com.rhcloud.msdm.conference.service.Interfaces;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Offer;
import com.rhcloud.msdm.conference.domain.entities.QueryConference;
import com.rhcloud.msdm.conference.domain.entities.Speaker;


import java.util.List;

/**
 * Created by Ghost on 17.10.2015.
 */
public interface SpeakerAction
{
     void save(Speaker speaker);

     void delete(Speaker speaker);

     List<Speaker> getAll();

     void update(Speaker speaker);

     Speaker getById(Integer id);

     List<Conference> getConference(Speaker speaker);

     void updatePhoto(Speaker speaker);

     List<Offer> getOffers(Speaker speaker);

     List<QueryConference> getQueries(Speaker speaker);

      Speaker getSpeakerByOffer(Offer o);

     Speaker getByUserName(String username);

     void agree(Offer offer);
}

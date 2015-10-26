package com.rhcloud.msdm.conference.service.Impl;



import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Offer;
import com.rhcloud.msdm.conference.domain.entities.QueryConference;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.repository.SpeakerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.SpeakerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ghost on 17.10.2015.
 */
@Service
public class SpeakerService implements SpeakerAction {

    @Autowired
    private SpeakerRepository repository;

    @Autowired
    private ConferenceRepository conferenceRepository;


    @Override
    public void save(Speaker speaker) {
            this.repository.save(speaker);
    }

    @Override
    public void delete(Speaker speaker) {
        this.repository.delete(speaker);
    }

    @Override
    public List<Speaker> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void update(Speaker speaker) {
        repository.saveAndFlush(speaker);
    }

    @Override
    public Speaker getById(Integer id) {
        return  repository.findOne(id);
    }

    @Override
    @Transactional
    public List<Conference> getConference(Speaker speaker) {
        return  repository.getSpeakerJoinConferenceById(speaker.getId()).getConferences();
    }



    @Override
    public void updatePhoto(Speaker speaker) {
        repository.updatePhoto(speaker.getId() , speaker.getProfileImage());
    }

    @Override
    public List<Offer> getOffers(Speaker speaker) {
      Speaker res =   repository.getSpeakerJoinOffersById(speaker.getId());
      return   res.getOffers();
    }

    public Speaker getSpeakerByOffer(Offer o){
        Speaker res = repository.getSpeakerJoinOffersById(o.getSpeaker().getId());
        return  res;
    }

    @Override
    public Speaker getByUserName(String username) {
        return  repository.getByUserName(username);
    }

    @Override
    @Transactional
    public void agree(Offer offer) {
        Speaker speaker = repository.getSpeakerJoinConferenceById(offer.getSpeaker().getId());
        Conference conference = conferenceRepository.getConferenceJoinSpeakerById(offer.getConference().getId());
        conference.addSpeaker(speaker);
        speaker.addConference(conference);
        repository.saveAndFlush(speaker);
    }

    @Override
    public List<QueryConference> getQueries(Speaker speaker) {
        Speaker res = repository.getSpeakerJoinQueryToConferencesById(speaker.getId());
        return  res.getQueryConferenceList();
    }

    public List<QueryConference> getQueryConference(Speaker speaker){
        return  repository.getSpeakerJoinQueryToConferencesById(speaker.getId()).getQueryConferenceList();
    }
}

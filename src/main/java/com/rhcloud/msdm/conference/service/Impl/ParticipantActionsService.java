package com.rhcloud.msdm.conference.service.Impl;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.repository.ParticipantRepository;
import com.rhcloud.msdm.conference.service.Interfaces.ParticipantActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("conferenceActionsService")
public class ParticipantActionsService implements ParticipantActions{

    @Autowired ConferenceRepository conferenceRepository;
    @Autowired ParticipantRepository participantRepository;

    @Override
    public List<Conference> findAllConferences() {
        return conferenceRepository.findAll();
    }

    @Override
    public Conference findOneConference(Integer id) {
        return conferenceRepository.getOne(id);
    }
}

package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.repository.ParticipantRepository;
import com.rhcloud.msdm.conference.repository.SpeakerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements Authorization {

    @Autowired ParticipantRepository participantRepository;
    @Autowired SpeakerRepository speakerRepository;
    @Autowired OrganizerRepository organizerRepository;

    //Mock
    @Override
    public boolean validateData(User user) {
        return true;
    }

    @Override
    public User authorizeUser(User user) {
        if (validateData(user)) {
            if (user instanceof Participant)
                return participantRepository.findParticipantByEmailAndUserName(user.getEmail(), user.getUserName());
            else if (user instanceof Speaker)
                return speakerRepository.findSpeakerByEmailAndUserName(user.getEmail(), user.getUserName());
            else if (user instanceof Organizer)
                return organizerRepository.findOrganizerByEmailAndUserName(user.getEmail(), user.getUserName());
        }
        return null;
    }
}
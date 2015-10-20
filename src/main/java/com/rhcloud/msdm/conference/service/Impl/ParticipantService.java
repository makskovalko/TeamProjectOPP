package com.rhcloud.msdm.conference.service.Impl;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.repository.ParticipantRepository;
import com.rhcloud.msdm.conference.service.Interfaces.ParticipantActions;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("participantActionsService")
public class ParticipantService implements ParticipantActions{

    @Autowired ConferenceRepository conferenceRepository;
    @Autowired ParticipantRepository participantRepository;

    @Override
    public Conference findOneConference(Integer id) {
        return conferenceRepository.getOne(id);
    }


    @Transactional
    @Override
    public Participant updateData(ProfileData profileData) {

        Participant newParticipant = participantRepository.findParticipantByEmailOrUserName("", profileData.getUserName());
        newParticipant.setFirstName(profileData.getFirstName());
        newParticipant.setLastName(profileData.getLastName());
        newParticipant.setPhoneNumber(profileData.getPhoneNumber());
        newParticipant.setDateOfBirth(profileData.getDateOfBirth());

        participantRepository.saveAndFlush(newParticipant);

        return newParticipant;
    }


    @Transactional(readOnly = true)
    @Override
    public Participant getParticipantByUserName(String userName) {
        return participantRepository.findParticipantByEmailOrUserName("", userName);
    }
}

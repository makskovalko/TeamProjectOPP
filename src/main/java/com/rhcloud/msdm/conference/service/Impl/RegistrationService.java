package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.repository.ParticipantRepository;
import com.rhcloud.msdm.conference.repository.SpeakerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements Registration {

    @Autowired private ParticipantRepository participantRepository;
    @Autowired private SpeakerRepository speakerRepository;
    @Autowired private OrganizerRepository organizerRepository;

    @Override
    public boolean checkData(User user) {
        if (user instanceof Participant) {
            if (participantRepository.findParticipantByEmailOrUserName(user.getEmail(), user.getUserName()) != null)
                return false;
        } else if (user instanceof Speaker) {
            if (speakerRepository.findSpeakerByEmailOrUserName(user.getEmail(), user.getUserName()) != null)
                return false;
        } else if (user instanceof Organizer) {
            if (organizerRepository.findOrganizerByEmailOrUserName(user.getEmail(), user.getUserName()) != null)
                return false;
        }

        if (validateData(user)) registerUser(user);
        return true;
    }

    @Override
    public void registerUser(User user) {
        if (user instanceof Participant) participantRepository.saveAndFlush((Participant) user);
        else if (user instanceof Speaker) speakerRepository.saveAndFlush((Speaker) user);
        else if (user instanceof Organizer) organizerRepository.saveAndFlush((Organizer) user);
    }

    //Mock
    @Override
    public boolean validateData(User user) {
        return true;
    }

    @Override
    public boolean confirmRegistration(String user, String userName, String confirmKey) {
        switch (user) {
            case "participant":
                participantRepository.confirmRegistration(userName, confirmKey);
                return true;
            case "speaker":
                speakerRepository.confirmRegistration(userName, confirmKey);
                return true;
            case "organizer":
                organizerRepository.confirmRegistration(userName, confirmKey);
                return true;
        }
        return false;
    }
}
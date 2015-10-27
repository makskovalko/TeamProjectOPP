package com.rhcloud.msdm.conference.service.Interfaces;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;

import java.util.List;

public interface ParticipantActions {

    Conference findOneConference(Integer id);

    Participant updateData(ProfileData profileData);
    Participant getParticipantByUserName(String userName);

    List<Conference> getUserConferences(Integer id);
}

package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Messages;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferenceJSON;
import com.rhcloud.msdm.conference.utils.JSON_POJO.MessageJSON;

public interface OrganizerActions {
    public void updateData(Organizer organizer);
    public Organizer getOrganizerByUserName(String userName);
    public Conference createConference(ConferenceJSON conferenceJSON);

    Organizer getOrganizerById(Integer id);
    public Messages sendMessage(MessageJSON messageJSON);
    //public List<Conference> findConferencesByName(String name);
}
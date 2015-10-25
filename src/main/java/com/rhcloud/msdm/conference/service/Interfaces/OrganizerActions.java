package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;

import java.util.List;

public interface OrganizerActions {
    public void updateData(Organizer organizer);
    public Organizer getOrganizerByUserName(String userName);
    public Conference createConference(ConferenceJSON conferenceJSON);
    public List<Conference> findConferencesByName(String name);
}
package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;

public interface OrganizerActions {
    public void updateData(Organizer organizer);
    public Organizer getOrganizerByUserName(String userName);
    public Conference createConference(ConferenceJSON conferenceJSON);
}
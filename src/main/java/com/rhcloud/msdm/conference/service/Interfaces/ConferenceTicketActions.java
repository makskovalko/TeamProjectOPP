package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.Conference;

import java.util.List;

public interface ConferenceTicketActions {

    Conference getConferenceById(Integer id);

    List<Conference> getLastConference(Integer number);


}

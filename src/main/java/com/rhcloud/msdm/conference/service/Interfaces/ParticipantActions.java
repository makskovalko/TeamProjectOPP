package com.rhcloud.msdm.conference.service.Interfaces;


import com.rhcloud.msdm.conference.domain.entities.Conference;

import java.util.List;

public interface ParticipantActions {

    List<Conference> findAllConferences();

    Conference findOneConference(Integer id);
}

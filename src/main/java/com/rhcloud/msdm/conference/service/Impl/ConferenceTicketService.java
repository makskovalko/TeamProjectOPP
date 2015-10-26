package com.rhcloud.msdm.conference.service.Impl;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.service.Interfaces.ConferenceTicketActions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("conferenceTicketService")
public class ConferenceTicketService implements ConferenceTicketActions {

    @Resource(name = "conferenceRepository")
    private ConferenceRepository conferenceRepository;


    @Override
    public Conference getConferenceById(Integer id) {
        return conferenceRepository.findOne(id);
    }


    @Override
    public List<Conference> getLastConference(Integer number) {

        int lastConferenceID = conferenceRepository.findMaxID();
        if (lastConferenceID < number) {
            return conferenceRepository.findAllConferenceByIdBetween(1, number + 1);
        }
        return conferenceRepository.findAllConferenceByIdBetween(lastConferenceID - number, number + 1);
    }
}

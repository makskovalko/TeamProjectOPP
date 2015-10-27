package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.repository.SpeakerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.SpeakerActions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("speakerService")
public class SpeakerService implements SpeakerActions {


    @Resource(name = "speakerRepository")
    private SpeakerRepository speakerRepository;

    @Override
    public List<Conference> getUserConferences(Integer id) {
        return speakerRepository.findOne(id).getConferences();
    }
}

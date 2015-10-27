package com.rhcloud.msdm.conference.controller;


import com.rhcloud.msdm.conference.domain.entities.Messages;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.OrganizerActions;
import com.rhcloud.msdm.conference.utils.JSON_POJO.MessageJSON;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpeakerController {


    @Resource(name = "organizerService")
    private OrganizerActions organizerActions;

    @Resource(name = "organizerRepository")
    private OrganizerRepository organizerRepository;

    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    public String getMessage(@RequestBody MessageJSON messageJSON){

        organizerActions.sendMessage(messageJSON);

        return "Ok";
    }


}

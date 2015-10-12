package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;
import com.rhcloud.msdm.conference.service.Impl.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @RequestMapping(value = "/updateOrganizerData", method = RequestMethod.POST)
    public String updateOrganizerData(@RequestBody Organizer organizer, HttpSession session) {
        organizerService.updateData(organizer);

        Organizer sessionOrganizer = (Organizer) session.getAttribute("user");
        sessionOrganizer.setFirstName(organizer.getFirstName());
        sessionOrganizer.setLastName(organizer.getLastName());
        //sessionOrganizer.setEmail(organizer.getEmail());
        sessionOrganizer.setPhoneNumber(organizer.getPhoneNumber());
        sessionOrganizer.setDateOfBirth(organizer.getDateOfBirth());

        return "OK";
    }

    @RequestMapping(value = "/create_conference", method = RequestMethod.POST)
    public String createConference(@RequestBody ConferenceJSON conferenceJSON) {
        organizerService.createConference(conferenceJSON);
        return "OK";
    }
}
package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;
import com.rhcloud.msdm.conference.service.Impl.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

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

    @RequestMapping(value = "/search_conference", method = RequestMethod.POST)
    public List<Conference> searchConferences(@RequestParam(value = "search") String searchString) {
        List<Conference> conferenceList = organizerService.findConferencesByName(searchString);
        for (Conference conference : conferenceList) JOptionPane.showMessageDialog(null, conference.getName());
        return conferenceList;
    }
}
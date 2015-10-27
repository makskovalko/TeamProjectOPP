package com.rhcloud.msdm.conference.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Messages;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.repository.MessagesRepository;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferenceJSON;
import com.rhcloud.msdm.conference.service.Impl.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @Resource(name = "messagesRepository")
    private MessagesRepository messagesRepository;

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

    @RequestMapping(value = "/search_conference/{search}", method = RequestMethod.GET)
    public ResponseEntity<String> searchConferences(@PathVariable("search") String searchString) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        List<Conference> conferenceList = organizerService.findConferencesByName(searchString);
        String resultJson = "";
        List<String> conferenceJSONList = new ArrayList<String>();

        for (Conference conference : conferenceList) {
            ConferenceJSON conferenceJSON = new ConferenceJSON(conference);
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(conferenceJSON);
                conferenceJSONList.add(json);
            } catch (JsonProcessingException e) {
                return new ResponseEntity<String>("Fail", httpHeaders, org.springframework.http.HttpStatus.OK);
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            resultJson = mapper.writeValueAsString(conferenceJSONList);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>("Fail", httpHeaders, org.springframework.http.HttpStatus.OK);
        }
        return new ResponseEntity<String>(resultJson, httpHeaders, org.springframework.http.HttpStatus.OK);
    }

    @RequestMapping(value = "/checkMessages/{id}", method = RequestMethod.GET)
    public Integer checkMessages(@PathVariable("id") Integer id){

        return messagesRepository.getCountMassageForUser(id);
    }

}
package com.rhcloud.msdm.conference.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Messages;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferenceJSON;
import com.rhcloud.msdm.conference.service.Impl.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping(value = "/search_conference_filter", method = RequestMethod.POST)
    public ResponseEntity<String> searchConferenceByFilter(@RequestParam(value = "country") String country,
                                           @RequestParam(value = "city") String city,
                                           @RequestParam(value = "categories") String categories) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        country = country.trim();
        city = city.trim();
        String[] arr = categories.split(";");
        Integer[] Arr = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) Arr[i] = Integer.valueOf(arr[i]);

        List<Integer> ids = Arrays.asList(Arr);
        List<Conference> conferenceList = new ArrayList<Conference>();

        System.out.println(arr.length);

        if (arr.length == 0) {
            if (!country.equals("") && !city.equals(""))
                conferenceList = organizerService.findConferencesByCountryAndCity(country, city);
            else if ((!country.equals("") && city.equals("")) || (country.equals("") && !city.equals("")))
                conferenceList = organizerService.findConferenceByCountryOrCity(country, city);
        } else {
            if (!country.equals("") && !city.equals(""))
                conferenceList = organizerService.findConferencesByCountryAndCityAndCategory_IdIn(country, city, ids);
            else if ((!country.equals("") && city.equals("")) || (country.equals("") && !city.equals(""))) {
                conferenceList = organizerService.findConferencesByCountryOrCityAndCategory_IdIn(country, city, ids);
            } else
                conferenceList = organizerService.findConferencesByCategory_Id(ids);
        }

        String resultJson = "";
        List<String> confererenceJSONList = new ArrayList<String>();
        for (Conference conference : conferenceList) {
            ConferenceJSON conferenceJSON = new ConferenceJSON(conference);
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(conferenceJSON);
                confererenceJSONList.add(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResponseEntity<String>("Fail!", httpHeaders, HttpStatus.OK);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            resultJson = mapper.writeValueAsString(confererenceJSONList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Fail!", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<String>(resultJson, httpHeaders, HttpStatus.OK);
    }
}
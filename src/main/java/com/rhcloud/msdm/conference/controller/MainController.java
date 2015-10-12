package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.repository.CategoryRepository;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ConferenceRepository conferenceRepository;


    @RequestMapping(value = "/profile/participant", method = RequestMethod.GET)
    public String participantView() {
        return "profiles/participant";
    }

    @RequestMapping(value = "/profile/organizer", method = RequestMethod.GET)
    public String organizerView(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("conferenceCategories", categoryRepository.findAll());
        modelMap.addAttribute("allConferences", conferenceRepository.findAll());
        modelMap.addAttribute("myCreatedConferences",
                conferenceRepository.findAllConferencesByOrganizerId(((Organizer)session.getAttribute("user")).getId()));
        return "profiles/organizer";
    }

    @RequestMapping(value = "/profile/speaker", method = RequestMethod.GET)
    public String speakerView() {
        return "profiles/speaker";
    }

}
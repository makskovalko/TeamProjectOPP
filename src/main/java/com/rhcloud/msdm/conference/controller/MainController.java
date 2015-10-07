package com.rhcloud.msdm.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/profile/participant", method = RequestMethod.GET)
    public String participantView() {
        return "profiles/participant";
    }

    @RequestMapping(value = "/profile/organizer", method = RequestMethod.GET)
    public String organizerView() {
        return "profiles/organizer";
    }

    @RequestMapping(value = "/profile/speaker", method = RequestMethod.GET)
    public String speakerView() {
        return "profiles/speaker";
    }

}
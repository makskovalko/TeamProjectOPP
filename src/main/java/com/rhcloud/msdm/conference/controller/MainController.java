package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Impl.AuthorizationService;
import com.rhcloud.msdm.conference.service.Impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "OpenShift");
        return "index";
    }

    //Mock
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    @ResponseBody
    public String signUp(HttpServletRequest request) {
        Organizer organizer = new Organizer();
        organizer.setUserName("Test");
        organizer.setPassword("123");
        organizer.setEmail("test@gmail.com");
        organizer.setFirstName("Test");
        organizer.setLastName("Testov");

        Participant participant = new Participant();
        participant.setUserName("Test");
        participant.setPassword("123");
        participant.setEmail("test@gmail.com");
        participant.setFirstName("Test");
        participant.setLastName("Testov");

        Speaker speaker = new Speaker();
        speaker.setUserName("Test1");
        speaker.setPassword("123");
        speaker.setEmail("test1@gmail.com");
        speaker.setFirstName("Test");
        speaker.setLastName("Testov");

        if (registrationService.checkData(participant)) return participant.getConfirmationKey(); /*"User was registered successfully! :)";*/
        else return "Fail";
    }

    //Mock
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    @ResponseBody
    public String signIn(HttpServletRequest request) {

        Speaker speaker = new Speaker();
        speaker.setUserName("Test1");
        speaker.setPassword("123");
        speaker.setEmail("test1@gmail.com");
        speaker.setFirstName("Test");
        speaker.setLastName("Testov");

        if (authorizationService.authorizeUser(speaker) != null) request.getSession().setAttribute("user", speaker);
        return ((User)request.getSession().getAttribute("user")).getUserName();
    }
}
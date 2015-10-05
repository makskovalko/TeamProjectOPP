package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Impl.AuthorizationService;
import com.rhcloud.msdm.conference.service.Impl.RegistrationService;
import com.rhcloud.msdm.conference.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthorizationService authorizationService;


    @Resource(name = "mailSenderService")
    private MailSender mailSender;


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
        participant.setUserName("TestParticipant");
        participant.setPassword("123");
        participant.setEmail("test_participant@gmail.com");
        participant.setFirstName("Test");
        participant.setLastName("Testov");

        Speaker speaker = new Speaker();
        speaker.setUserName("Test1");
        speaker.setPassword("123");
        speaker.setEmail("test1@gmail.com");
        speaker.setFirstName("Test");
        speaker.setLastName("Testov");

        if (registrationService.checkData(participant)) {
            //request.getSession().setAttribute("");

            return "User was registered successfully! :)";
        } else return "Fail";
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
        return ((User) request.getSession().getAttribute("user")).getUserName();
    }



    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public String sendMail() {

        String to = "alexstingtalanov@gmail.com";
        String subject = "MSDM";
        String body = "Hello!!!";

        mailSender.sendMail(to, subject, body);

        return "index";
    }


}
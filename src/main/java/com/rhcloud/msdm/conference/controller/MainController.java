package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Impl.AuthorizationService;
import com.rhcloud.msdm.conference.service.Impl.RegistrationService;
import com.rhcloud.msdm.conference.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signUp(HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        Organizer organizer = new Organizer();
        organizer.setUserName("Test");
        organizer.setPassword("123");
        organizer.setEmail("test@gmail.com");
        organizer.setFirstName("Test");
        organizer.setLastName("Testov");

        Participant participant = new Participant();
        participant.setUserName("makskovalko");
        participant.setPassword("7777777");
        participant.setEmail("makskovalko@gmail.com");
        participant.setFirstName("Maxim");
        participant.setLastName("Kovalko");

        Speaker speaker = new Speaker();
        speaker.setUserName("Test1");
        speaker.setPassword("123");
        speaker.setEmail("test1@gmail.com");
        speaker.setFirstName("Test");
        speaker.setLastName("Testov");

        if (registrationService.checkData(participant)) {

            //return participant.getConfirmationKey(); /*"User was registered successfully! :)";*/
            mailSender.sendMail(participant.getEmail(), "Conference Registration", participant.getConfirmURL());
            return new ResponseEntity<String>("Подтвердите свою регистрацию, перейдя по ссылке в письме, высланном Вам на e-mail", httpHeaders, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Ошибка регистрации! Пользователь с такими данными уже зарегистрирован!", httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/confirm_email/{user}/{userName}/{confirmKey}")
    @ResponseBody
    public ResponseEntity<String> confirmEmail(@PathVariable("user") String user,
                                               @PathVariable("userName") String userName,
                                               @PathVariable(value = "confirmKey") String confirmKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        if (registrationService.confirmRegistration(user, userName, confirmKey))
            return new ResponseEntity<String>("Регистрация прошла успешно!", httpHeaders, HttpStatus.OK);
        else return new ResponseEntity<String>("Ошибка регистрации!", httpHeaders, HttpStatus.OK);

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

        String to = "makskovalko@gmail.com";
        String subject = "MSDM";
        String body = "Hello!!!";

        mailSender.sendMail(to, subject, body);

        return "index";
    }
}
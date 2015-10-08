package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Interfaces.ParticipantActions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class ParticipantController {

    @Resource(name = "conferenceActionsService")
    private ParticipantActions participantActions;

    @RequestMapping(value = "/participant", method = RequestMethod.GET)
    public String participantPage(Model model, HttpSession session){

        User user = (User) session.getAttribute("user");

        model.addAttribute("allConferences", participantActions.findAllConferences());
        model.addAttribute("userInformation", user);

        return "participant";
    }


    @RequestMapping(value = "/participant/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> participantEdit(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/plain;charset=UTF-8");



        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/participant/editPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> participantEditPassword(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/plain;charset=UTF-8");



        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/participant/editEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> participantEditEmail(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/plain;charset=UTF-8");



        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }

}

package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.service.Interfaces.ParticipantActions;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Resource(name = "participantActionsService")
    private ParticipantActions participantActions;


    @RequestMapping(value = "/updateData", method = RequestMethod.POST, consumes = "application/json")
    public ProfileData participantUpdateData(@RequestBody ProfileData profileData, HttpSession session){

        session.setAttribute("user", participantActions.updateData(profileData));
        return profileData;
    }




}
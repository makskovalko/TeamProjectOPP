package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Interfaces.ConferenceTicketActions;
import com.rhcloud.msdm.conference.service.Interfaces.ParticipantActions;
import com.rhcloud.msdm.conference.utils.FileUploader;
import com.rhcloud.msdm.conference.utils.GoogleDriveService;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Resource(name = "participantActionsService")
    private ParticipantActions participantActions;

    @Resource(name = "conferenceTicketService")
    private ConferenceTicketActions conferenceTicketActions;


    @RequestMapping(value = "/updateData", method = RequestMethod.POST, consumes = "application/json")
    public ProfileData participantUpdateData(@RequestBody ProfileData profileData, HttpSession session){

        session.setAttribute("user", participantActions.updateData(profileData));
        return profileData;
    }

    @Resource(name = "fileUploaderService")
    private FileUploader fileUploaderService;

    @Resource(name = "googleDriveService")
    private GoogleDriveService googleDriveService;

    @RequestMapping(value = "/getParticipantsForThisConference/{id}", method = RequestMethod.GET)
    public List<User> getParticipantsForThisConference(@PathVariable("id") Integer id) throws IOException, GeneralSecurityException {

        List<User> usersList = new ArrayList<>();
        for(Participant participant : conferenceTicketActions.getConferenceById(id).getParticipants()){
            User user = new User();
            user.setUserName(participant.getUserName());
            user.setFirstName(participant.getFirstName());
            user.setLastName(participant.getLastName());
            user.setDateOfBirth(participant.getDateOfBirth());
            user.setEmail(participant.getEmail());
            user.setPhoneNumber(participant.getPhoneNumber());

            if ( fileUploaderService.fileExists(participant.getUserName()) != null || googleDriveService.download(participant.getUserName())) {
                user.setProfileImage("../resources/ProfileImagesBufferDir/" + fileUploaderService.fileExists(participant.getUserName()).getName());
            } else {
                user.setProfileImage("../resources/img/default.gif");
            }

            usersList.add(user);
        }

        return usersList;
    }



}